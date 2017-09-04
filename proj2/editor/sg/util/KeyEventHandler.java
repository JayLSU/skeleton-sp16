package sg.util;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


/** An EventHandler to handle keys that get pressed. */
public class KeyEventHandler implements EventHandler<KeyEvent> {
    private static final int MARGIN = 5;
    private static final String MESSAGE_PREFIX =
            "User pressed the shortcut key (command or control, depending on the OS)";


    private static final int STARTING_FONT_SIZE = 12;

    private FastLinkedList allToDisplay = new FastLinkedList();
    /** The Text to display on the screen. */
    //private Text displayText = new Text(STARTING_TEXT_POSITION_X + MARGIN, STARTING_TEXT_POSITION_Y, "");
    private int fontSize = STARTING_FONT_SIZE;
    private String filename;
    private Text tempText = new Text(250,250,"Test");
    private Group temproot;

    public KeyEventHandler(final Group root, String name, FastLinkedList InitialDis) {
        // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
        // that when the text is assigned a y-position, that position corresponds to the
        // highest position across all letters (for example, the top of a letter like "I", as
        // opposed to the top of a letter like "e"), which makes calculating positions much
        // simpler!
        filename = name;
        temproot = root;

        allToDisplay = InitialDis;
        AddContentToRoot(temproot, allToDisplay);

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
                allToDisplay.XYPosUpdate();
                temproot.getChildren().add(allToDisplay.getCurrentNode().nodeText);
                keyEvent.consume();
            }else if (characterTyped.length() > 0 && characterTyped.charAt(0) == 8) {
                // Ignore control keys, which have non-zero length, as well as the backspace
                // key, which is represented as a character of value = 8 on Windows.
                temproot.getChildren().remove(allToDisplay.getCurrentNode().nodeText);
                allToDisplay.delete();
                allToDisplay.XYPosUpdate();

            }

        } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
            // events have a code that we can check (KEY_TYPED events don't have an associated
            // KeyCode).
            KeyCode code = keyEvent.getCode();
            if (code == KeyCode.UP) {
                fontSize += 5;
            } else if (code == KeyCode.DOWN) {
                fontSize = Math.max(0, fontSize - 5);
            }
        }
    }

}

