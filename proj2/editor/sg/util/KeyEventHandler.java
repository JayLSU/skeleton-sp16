package sg.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.security.AlgorithmConstraints;


/** An EventHandler to handle keys that get pressed. */
public class KeyEventHandler implements EventHandler<KeyEvent> {
    private static final int MARGIN = 5;


    private static final int STARTING_FONT_SIZE = 20;

    private FastLinkedList allToDisplay = new FastLinkedList();
    /** The Text to display on the screen. */
    //private Text displayText = new Text(STARTING_TEXT_POSITION_X + MARGIN, STARTING_TEXT_POSITION_Y, "");
    private int fontSize = STARTING_FONT_SIZE;
    private String filename;
    private Group temproot;
    private Rectangle cursor = new javafx.scene.shape.Rectangle(1, 24);
    private LineStarterArray<FastLinkedList.Node> LineStarterS = new LineStarterArray<>();

    public KeyEventHandler(final Group root, String name, FastLinkedList InitialDis, Rectangle R, LineStarterArray<FastLinkedList.Node> S) {
        // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
        // that when the text is assigned a y-position, that position corresponds to the
        // highest position across all letters (for example, the top of a letter like "I", as
        // opposed to the top of a letter like "e"), which makes calculating positions much
        // simpler!
        filename = name;
        temproot = root;
        cursor = R;
        cursor.setX(MARGIN);
        allToDisplay = InitialDis;
        LineStarterS = S;
        AddContentToRoot(temproot, allToDisplay);
        temproot.getChildren().add(cursor);
        makeCursorColorChange();
        allToDisplay.CurrentPosUpdate();
        cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
    }


    private void AddContentToRoot(Group root, FastLinkedList InitialDis){
        if (!InitialDis.isEmpty()){
            FastLinkedList.Node tempNode = InitialDis.getStartNode();
            while(tempNode != null){
                root.getChildren().add(tempNode.nodeText);
                tempNode = tempNode.next;
            }
        }
    }


    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.isShortcutDown()) {
            if (keyEvent.getCode() == KeyCode.A) {
                System.out.println("User pressed the shortcut key (command or control, depending on the OS)" + " in addition to \"a\"");
            } else if (keyEvent.getCode() == KeyCode.Z) {
                System.out.println("User pressed the shortcut key (command or control, depending on the OS)" + " in addition to \"z\"");
            } else if (keyEvent.getCode() == KeyCode.S){
                SaveFile.save(filename, allToDisplay);
            } else if (keyEvent.getCode() == KeyCode.PLUS || keyEvent.getCode() == KeyCode.EQUALS){
                fontSize += 4;
                allToDisplay.fontUpdate(fontSize);
                WordWrap.warp(allToDisplay);
                LineStarterS = WordWrap.getStarterA();
                //allToDisplay.XYPosUpdate();
                allToDisplay.CurrentPosUpdate();
                if (allToDisplay.getCurrentNode().nodeText.getText().equals("\n")){
                    double deltaH = allToDisplay.getCursorHeight();
                    allToDisplay.CurrentPosY = allToDisplay.getCurrentNode().nodeText.getY()+ deltaH;
                }

                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
                cursorSizeUpdate(allToDisplay.getCursorHeight());
            } else if (keyEvent.getCode() == KeyCode.MINUS){
                fontSize = Math.max(0, fontSize - 4);
                allToDisplay.fontUpdate(fontSize);
                WordWrap.warp(allToDisplay);
                LineStarterS = WordWrap.getStarterA();
                //allToDisplay.XYPosUpdate();
                allToDisplay.CurrentPosUpdate();
                if (allToDisplay.getCurrentNode().nodeText.getText().equals("\n")){
                    double deltaH = allToDisplay.getCursorHeight();
                    allToDisplay.CurrentPosY = allToDisplay.getCurrentNode().nodeText.getY()+ deltaH;
                }
                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
                cursorSizeUpdate(allToDisplay.getCursorHeight());
            }
        }else if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
            // the KEY_TYPED event, javafx handles the "Shift" key and associated
            // capitalization.


            String characterTyped = keyEvent.getCharacter();


            if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.
                if (characterTyped.equals("\r")){
                    characterTyped = "\n";
                }
                allToDisplay.add(characterTyped);
                WordWrap.warp(allToDisplay);
                LineStarterS = WordWrap.getStarterA();
                //allToDisplay.XYPosUpdate();
                allToDisplay.CurrentPosUpdate();
                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
                temproot.getChildren().add(allToDisplay.getCurrentNode().nodeText);
                keyEvent.consume();
            }else if (characterTyped.length() > 0 && characterTyped.charAt(0) == 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.

                temproot.getChildren().remove(allToDisplay.getCurrentNode().nodeText);
                allToDisplay.delete();
                WordWrap.warp(allToDisplay);
                LineStarterS = WordWrap.getStarterA();
                //allToDisplay.XYPosUpdate();
                allToDisplay.CurrentPosUpdate();
                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
            }

        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
            // events have a code that we can check (KEY_TYPED events don't have an associated
            // KeyCode).
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.UP) {

                // Keyevent with up arrow key
                UpArrowEvent();

            } else if (code == KeyCode.DOWN) {

                // Keyevent with down arrow key
                DownArrowEvent();
            } else if (code == KeyCode.LEFT){
                LeftArrowEvent();
            } else if (code == KeyCode.RIGHT){
                RightArrowEvent();
            }
        }
    }

    private void cursorPosUpdate(double x, double y){
        cursor.setX(x);
        cursor.setY(y);
    }

    private void UpArrowEvent(){
        if (!allToDisplay.isEmpty()){
            double PosXRecord = MARGIN;
            double TempCurPosX = allToDisplay.getCurrentPosX();
            double TempCurPosY = allToDisplay.getCurrentPosY();
            double CurrentLineNo = (TempCurPosY/allToDisplay.getCursorHeight() + 1);
            double preLineY;
            double diffX = 0;
            double diffXpre = Math.abs(TempCurPosX - PosXRecord);
            FastLinkedList.Node lastLineStarter;
            if (CurrentLineNo != 1.0){
                lastLineStarter = LineStarterS.get((int)CurrentLineNo - 2);
                preLineY = lastLineStarter.nodeText.getY();
                while(true){
                    diffX = Math.abs(TempCurPosX - lastLineStarter.nodeText.getX() - Math.round(lastLineStarter.nodeText.getLayoutBounds().getWidth()));

                    if (lastLineStarter.next == null){
                        allToDisplay.setCurNode(lastLineStarter.pre);
                        break;
                    }

                    if (lastLineStarter.next.nodeText.getY()!=preLineY || diffXpre < diffX || diffX == 0){
                        if (diffX ==0 && lastLineStarter.next.nodeText.getY()==preLineY){
                            allToDisplay.setCurNode(lastLineStarter);
                            break;
                        }
                        allToDisplay.setCurNode(lastLineStarter.pre);
                        break;
                    }
                    diffXpre = diffX;
                    lastLineStarter = lastLineStarter.next;
                    //PosXRecord = lastLineStarter.nodeText.getX();
                }
                allToDisplay.CurrentPosUpdate();
                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
            }
        }

    }

    private void DownArrowEvent(){
        if (!allToDisplay.isEmpty() && (allToDisplay.getCurrentNode().next != null) ){ // List is not empty and the current node is not the last node
            double PosXRecord = MARGIN;
            double TempCurPosX = allToDisplay.getCurrentPosX();
            double TempCurPosY = allToDisplay.getCurrentPosY();
            double CurrentLineNo = (TempCurPosY/allToDisplay.getCursorHeight() + 1);
            double preLineY;
            double diffX = 0;
            double diffXpre = Math.abs(TempCurPosX - PosXRecord);
            double totalLine = LineStarterS.getTotalLine();
            FastLinkedList.Node nextLineStarter;
            // If current line is not the last line or the end of line is "\n"
            if (CurrentLineNo != totalLine || allToDisplay.getCurrentNode().next.nodeText.getText().equals("\n")){
                nextLineStarter = LineStarterS.get((int)CurrentLineNo);
                if (nextLineStarter == null){ // the end of the last line is "\n"
                    allToDisplay.setCurNode(allToDisplay.getCurrentNode().next);
                }else{
                    preLineY = nextLineStarter.nodeText.getY();
                    while(true){
                        diffX = Math.abs(TempCurPosX - nextLineStarter.nodeText.getX() - Math.round(nextLineStarter.nodeText.getLayoutBounds().getWidth()));
                        if (nextLineStarter.next!=null){
                            if (nextLineStarter.next.nodeText.getY()!=preLineY || diffXpre < diffX || diffX == 0){
                                if (diffX ==0 && (nextLineStarter.next.nodeText.getY()==preLineY )){
                                    allToDisplay.setCurNode(nextLineStarter);
                                    break;
                                }
                                allToDisplay.setCurNode(nextLineStarter.pre);
                                break;
                            }
                            diffXpre = diffX;
                            nextLineStarter = nextLineStarter.next;
                        }else{
                            if (nextLineStarter.nodeText.getText().equals("\n")){
                                allToDisplay.setCurNode(nextLineStarter.pre);
                                break;
                            }

                            allToDisplay.setCurNode(nextLineStarter);
                            break;
                        }

                        //PosXRecord = lastLineStarter.nodeText.getX();
                    }
                }


                allToDisplay.CurrentPosUpdate();
                cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
            }
        }

    }

    private void LeftArrowEvent(){
        if (!allToDisplay.isEmpty()){
            allToDisplay.setCurNode(allToDisplay.getCurrentNode().pre);
            allToDisplay.CurrentPosUpdate();
            cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
        }
    }

    private void RightArrowEvent(){
        if (!allToDisplay.isEmpty() && allToDisplay.getCurrentNode().next!=null){
            allToDisplay.setCurNode(allToDisplay.getCurrentNode().next);
            allToDisplay.CurrentPosUpdate();
            cursorPosUpdate(allToDisplay.getCurrentPosX(), allToDisplay.getCurrentPosY());
        }
    }

    private void cursorSizeUpdate(double y){
        cursor.setHeight(y);
    }

    private void makeCursorColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler(cursor);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

}

