package editor;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import sg.util.*;


public class Editor extends Application {
    private static int WINDOW_WIDTH = 500;
    private static int WINDOW_HEIGHT = 500;
    private static final int MARGIN = 5;
    private static FastLinkedList initialDisplay = new FastLinkedList();
    private static String filename;
    private Rectangle cursor = new Rectangle(1,24);
    private static LineStarterArray<FastLinkedList.Node> starter = new LineStarterArray();
    private double ClickPosX = 0.0;
    private double ClickPosY = 0.0;

    private int getDimensionInsideMargin(int outsideDimension) {
        return outsideDimension - MARGIN ;
    }
    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
        Group textRoot = new Group();
        root.getChildren().add(textRoot);
        //textRoot.setLayoutY(20)

        // Make a vertical scroll bar on the right side of the screen.
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        // Set the height of the scroll bar so that it fills the whole window.
        scrollBar.setPrefHeight(WINDOW_HEIGHT);

        // Set the range of the scroll bar.
        scrollBar.setMin(0);
        scrollBar.setMax(WINDOW_HEIGHT);

        // Add the scroll bar to the scene graph, so that it appears on the screen.
        root.getChildren().add(scrollBar);

        scrollBar.setLayoutX(486);


        /** When the scroll bar changes position, change the height of Josh. */
        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                // newValue describes the value of the new position of the scroll bar. The numerical
                // value of the position is based on the position of the scroll bar, and on the min
                // and max we set above. For example, if the scroll bar is exactly in the middle of
                // the scroll area, the position will be:
                //      scroll minimum + (scroll maximum - scroll minimum) / 2
                // Here, we can directly use the value of the scroll bar to set the height of Josh,
                // because of how we set the minimum and maximum above.


                double LineHeight = Math.round(initialDisplay.sentinal.nodeText.getLayoutBounds().getHeight());

                WordWrap.warp(initialDisplay);
                LineStarterArray<FastLinkedList.Node> LineStarterS = WordWrap.getStarterA();
                int numOfLine = LineStarterS.getTotalLine();

                int numOfInWindowLine = (int) Math.floor(WINDOW_HEIGHT/LineHeight);
                int numOfOutWindowLine = numOfLine - numOfInWindowLine;
                double TotalHeight = numOfLine * LineHeight;
                double move =-Math.max(0,newValue.doubleValue()/WINDOW_HEIGHT*(TotalHeight-WINDOW_HEIGHT));
                int Intmove = (int) (Math.round(move/LineHeight)*LineHeight);
                textRoot.setLayoutY(Intmove);
            }
        });



        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldScreenWidth,
                    Number newScreenWidth) {
                // Re-compute width.
                WINDOW_WIDTH = getDimensionInsideMargin(newScreenWidth.intValue() - (int) Math.round(0.3*scrollBar.getLayoutBounds().getWidth()));
                scrollBar.setLayoutX(newScreenWidth.doubleValue() - scrollBar.getLayoutBounds().getWidth());
                initialDisplay.setLineWidth(WINDOW_WIDTH);
                WordWrap.warp(initialDisplay);
                //LineStarterS = WordWrap.getStarterA();
                //allToDisplay.XYPosUpdate();
                initialDisplay.CurrentPosUpdate();
                cursorPosUpdate(initialDisplay.getCurrentPosX(), initialDisplay.getCurrentPosY());
            }
        });
        WINDOW_WIDTH = getDimensionInsideMargin(WINDOW_WIDTH - (int) Math.round(0.3*scrollBar.getLayoutBounds().getWidth()));
        initialDisplay.setLineWidth(WINDOW_WIDTH);
        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(textRoot, filename, initialDisplay, cursor, starter);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnMouseClicked(new MouseClickEventHandler(textRoot, KeyEventHandler.returnAllToDisplay(), KeyEventHandler.getCursor()));

        primaryStage.setTitle("SJ's Editor");
        // Initialize cursor parameters



        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void cursorPosUpdate(double x, double y){
        cursor.setX(x);
        cursor.setY(y);
    }
    private static LineStarterArray<FastLinkedList.Node> getInitiallistToStarterArray(FastLinkedList l){
        LineStarterArray<FastLinkedList.Node> InitialStarterArray = new LineStarterArray<>();
        if (!l.isEmpty()){
            FastLinkedList.Node tempScanNode = l.sentinal.next;
            while (tempScanNode != null){
                if (tempScanNode.nodeText.getX() == MARGIN){
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