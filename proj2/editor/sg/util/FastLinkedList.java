package sg.util;

import javafx.scene.text.Text;

public class FastLinkedList<Blorp> {
    private Node sentinal;


    class Node{
            Node pre, next;
            Blorp nodeText;
    }
}
