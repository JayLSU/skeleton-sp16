package sg.util;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MouseClickEventHandler implements EventHandler<MouseEvent> {
    /** A Text object that will be used to print the current mouse position. */
    Text positionText;
    private double mousePressedX;
    private double mousePressedY;
    private static int IntMove = 0;
    private static FastLinkedList allToDis = new FastLinkedList();
    private static LineStarterArray<FastLinkedList.Node> lineStarter = new LineStarterArray<>();
    private static Rectangle cursor = new javafx.scene.shape.Rectangle(1, 24);
    private static final int MARGIN = 5;

    public MouseClickEventHandler(Group root, FastLinkedList L, Rectangle C) {
        // For now, since there's no mouse position yet, just create an empty Text object.
        positionText = new Text("");
        // We want the text to show up immediately above the position, so set the origin to be
        // VPos.BOTTOM (so the x-position we assign will be the position of the bottom of the
        // text).
        positionText.setTextOrigin(VPos.BOTTOM);

        // Add the positionText to root, so that it will be displayed on the screen.
        root.getChildren().add(positionText);


        mousePressedX = 0;
        mousePressedY = 0;

        cursor = C;
        allToDis = L;
    }
    public static void setMove(int M){
        IntMove = M;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        lineStarter = WordWrap.getStarterA();
        // Because we registered this EventHandler using setOnMouseClicked, it will only called
        // with mouse events of type MouseEvent.MOUSE_CLICKED.  A mouse clicked event is
        // generated anytime the mouse is pressed and released on the same JavaFX node.
        mousePressedX = mouseEvent.getX();
        mousePressedY = mouseEvent.getY()-IntMove;
        currentNodeUpdate();

        // Display text right above the click.
        positionText.setText("(" + mousePressedX + ", " + mousePressedY + ")");
        positionText.setX(mousePressedX);
        positionText.setY(mousePressedY);
    }

    private void currentNodeUpdate(){
        double ClickX = mousePressedX;
        double ClickY = mousePressedY;
        double LineHeight = allToDis.getCursorHeight();
        double PosXRecord = MARGIN;
        int TolL = lineStarter.getTotalLine();
        double ClickL = Math.max(0,Math.floor(ClickY / LineHeight));
        FastLinkedList.Node ClickLStarter = lineStarter.get((int) ClickL);
        double ScanX = MARGIN;
        //double ScanY = ClickLStarter.nodeText.getY();
        double diffX = 0;
        double diffXpre = Math.abs(ClickX - PosXRecord);
        if (!allToDis.isEmpty()){
            if (ClickLStarter==null){// Click postion below the last line
                ClickLStarter = lineStarter.get(TolL-1);
                if (ClickLStarter.nodeText.getText().equals("\n")){// The last line only has \n
                    allToDis.setCurNode(ClickLStarter);
                    allToDis.CurrentPosUpdate();
                    cursorPosUpdate(allToDis.getCurrentPosX(), allToDis.getCurrentPosY());
                }else{ // The last line has other characters
                    while(ClickLStarter != null){
                        ScanX = ClickLStarter.nodeText.getX() + Math.round(ClickLStarter.nodeText.getLayoutBounds().getWidth());
                        diffX = Math.abs(ClickX - ScanX);
                        if (ClickLStarter.next != null){// The scan node ClickLStarter is not the last node
                            if (diffXpre < diffX || diffX ==0){// Previous node is the closest
                                allToDis.setCurNode(ClickLStarter.pre);
                                break;
                            }
                        }else{
                            if (diffXpre < diffX || diffX ==0){// Previous node is the closest
                                allToDis.setCurNode(ClickLStarter.pre);
                                break;
                            }else{
                                allToDis.setCurNode(ClickLStarter);
                                break;
                            }
                        }
                        diffXpre = diffX;
                        ClickLStarter = ClickLStarter.next;
                    }

                    allToDis.CurrentPosUpdate();
                    cursorPosUpdate(allToDis.getCurrentPosX(), allToDis.getCurrentPosY());
                    if (allToDis.getCurrentNode().next != null && !allToDis.getCurrentNode().nodeText.getText().equals("\n") && allToDis.getCurrentNode()!=null && allToDis.getCurrentNode().next.nodeText.getX() == MARGIN){// if current node is not \n and null, next node is line starter (caused by word wrap)
                        cursorPosUpdate(MARGIN, allToDis.getCurrentNode().next.nodeText.getY());
                    }
                }
            }else{// Click position in the text body
                double ScanY = ClickLStarter.nodeText.getY(); // Cursor Y position has to be the same with ScanY
                if (ClickX <= MARGIN){
                    allToDis.setCurNode(ClickLStarter.pre);
                    allToDis.CurrentPosUpdate();
                    cursorPosUpdate(MARGIN, allToDis.getCurrentNode().next.nodeText.getY());
                }else if(ClickX >= (allToDis.getLineWidth() - MARGIN)){
                    while(true){
                        if (ClickLStarter.next == null){
                            break;
                        }else if (ClickLStarter.next.nodeText.getX()==MARGIN){
                            break;
                        }
                        ClickLStarter = ClickLStarter.next;
                    }
                    if (ClickLStarter.nodeText.getText().equals("\n")){
                        allToDis.setCurNode(ClickLStarter.pre);
                    }else{
                        allToDis.setCurNode(ClickLStarter);
                    }

                    double LineLastPosX = Math.round(allToDis.getCurrentNode().nodeText.getX() + allToDis.getCurrentNode().nodeText.getLayoutBounds().getWidth());
                    allToDis.CurrentPosUpdate();
                    cursorPosUpdate(LineLastPosX, ScanY);

                }else{
                    while (true){
                        ScanX = ClickLStarter.nodeText.getX() + Math.round(ClickLStarter.nodeText.getLayoutBounds().getWidth());
                        diffX = Math.abs(ClickX - ScanX);
                        if (ClickLStarter.next == null){
                            allToDis.setCurNode(ClickLStarter);
                            break;
                        }
                        if (ClickLStarter.next.nodeText.getY() != ScanY || diffXpre < diffX || diffX == 0){
                            if (diffX == 0 && (ClickLStarter.next.nodeText.getY() == ScanY)) {
                                allToDis.setCurNode(ClickLStarter);
                                break;
                            }
                            if (ClickLStarter.next.nodeText.getY() != ScanY && !(diffXpre < diffX)){
                                allToDis.setCurNode(ClickLStarter);
                                break;
                            }
                            allToDis.setCurNode(ClickLStarter.pre);
                            break;
                        }
                        diffXpre = diffX;
                        ClickLStarter = ClickLStarter.next;
                    }
                    allToDis.CurrentPosUpdate();

                    if (allToDis.getCurrentNode().next == null){
                        double LineLastPosX = Math.round(allToDis.getCurrentNode().nodeText.getX() + allToDis.getCurrentNode().nodeText.getLayoutBounds().getWidth());
                        if (allToDis.getCurrentNode().nodeText.getText().equals("\n")){
                            allToDis.setCurNode(ClickLStarter.pre);
                            allToDis.CurrentPosUpdate();
                            cursorPosUpdate(LineLastPosX, ScanY);
                        }else{
                            cursorPosUpdate(LineLastPosX, ScanY);
                        }

                    }else if (allToDis.getCurrentNode().next.nodeText.getX() == MARGIN){
                        if (allToDis.getCurrentNode().nodeText.getText().equals("\n") && allToDis.getCurrentNode().nodeText.getY() == ScanY){
                            double LineLastPosX = Math.round(allToDis.getCurrentNode().nodeText.getX() + allToDis.getCurrentNode().nodeText.getLayoutBounds().getWidth());
                            allToDis.setCurNode(ClickLStarter.pre);
                            allToDis.CurrentPosUpdate();
                            cursorPosUpdate(LineLastPosX, ScanY);
                        }else{
                            if (allToDis.getCurrentNode().nodeText.getY() != ScanY){
                                cursorPosUpdate(MARGIN, ScanY);
                            }else{
                                cursorPosUpdate(allToDis.getCurrentPosX(), allToDis.getCurrentPosY());
                            }

                        }
                    }else{
                        cursorPosUpdate(allToDis.getCurrentPosX(), allToDis.getCurrentPosY());
                    }



                }
            }


        }
    }

    private void cursorPosUpdate(double x, double y){
        cursor.setX(x);
        cursor.setY(y);
    }
}
