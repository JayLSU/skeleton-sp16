package sg.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
    private Rectangle textBoundingBox = new Rectangle(1, 24);
    private int currentColorIndex = 0;
    private Color[] boxColors =
            {Color.BLACK, Color.WHITE};

    RectangleBlinkEventHandler(Rectangle R) {
        // Set the color to be the first color in the list.
        changeColor();
        textBoundingBox = R;
    }

    private void changeColor() {
        textBoundingBox.setFill(boxColors[currentColorIndex]);
        currentColorIndex = (currentColorIndex + 1) % boxColors.length;
    }

    @Override
    public void handle(ActionEvent event) {
        changeColor();
    }
}