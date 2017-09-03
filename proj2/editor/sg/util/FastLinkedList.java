package sg.util;

import javafx.scene.text.Text;

public class FastLinkedList {
    private Node sentinal;
    //private int currentPos;
    private Node currentNode;
    private int size;
    private static final int INITIAL_TEXT_POSITION_X = 0;
    private static final int INITIAL_TEXT_POSITION_Y = 0;

    public FastLinkedList(){
        this.sentinal = new Node(null, null, null);
        this.sentinal.pre = sentinal;
        this.sentinal.next = sentinal;
        this.currentNode = null;
        this.size = 0;
    }

    public Node getStartNode(){
        return sentinal.next;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    void add(String CharTyped){
        Node newNode = new Node(null, null, null);
        newNode.nodeText = new Text (INITIAL_TEXT_POSITION_X, INITIAL_TEXT_POSITION_Y, CharTyped);
        if (isEmpty()){
/*            newNode.pre = newNode;
            newNode.next = newNode;*/
            sentinal.next = newNode;
        }else{
            newNode.pre = currentNode;
            newNode.next = currentNode.next;
            if (currentNode.next != null){
                currentNode.next.pre = newNode;
            }
            currentNode.next = newNode;
        }
        size += 1;
        currentNode = newNode;
    }

    void delete(){
        if (!isEmpty()){
            if (currentNode.next!=null){
                currentNode.next.pre = currentNode.pre;
            }
            currentNode.pre.next = currentNode.next;
            currentNode = currentNode.pre;
            size -= 1;
        }
    }




    public class Node{
            public Node pre, next;
            public Text nodeText;
            Node(Node p, Node n, Text t){
                pre = p;
                next = n;
                nodeText = t;
            }
    }
}
