package editor;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import sg.util.FastLinkedList;
import sg.util.Print;
import sg.util.KeyEventHandler;
import sg.util.LineStarterArray;


public class Editor extends Application {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private static FastLinkedList initialDisplay = new FastLinkedList();
    private static String filename;
    private Rectangle cursor = new Rectangle(1,24);
    private static LineStarterArray<FastLinkedList.Node> starter = new LineStarterArray();


    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);



        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root, filename, initialDisplay, cursor, starter);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        primaryStage.setTitle("SJ's Editor");
        // Initialize cursor parameters



        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static LineStarterArray<FastLinkedList.Node> getInitiallistToStarterArray(FastLinkedList l){
        LineStarterArray<FastLinkedList.Node> InitialStarterArray = new LineStarterArray<>();
        if (!l.isEmpty()){
            FastLinkedList.Node tempScanNode = l.sentinal.next;
            while (tempScanNode != null){
                if (tempScanNode.nodeText.getX() == 5){
                    InitialStarterArray.addBack(tempScanNode);
                }
                tempScanNode = tempScanNode.next;
            }

        }

        return InitialStarterArray;
    }

    public static void main(String[] args) {
        initialDisplay = Print.print(args);
        starter = getInitiallistToStarterArray(initialDisplay);
        filename = args[0];
        launch(args);
    }
}