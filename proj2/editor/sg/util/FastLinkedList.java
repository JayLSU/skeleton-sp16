package sg.util;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FastLinkedList {
    private Node sentinal;
    private Node currentNode;
    private int size;
    private static final int INITIAL_TEXT_POSITION_X = 0;
    private static final int INITIAL_TEXT_POSITION_Y = 0;
    private static final int MARGIN = 5;
    private static final int STARTING_FONT_SIZE = 20;
    private int fontSize = STARTING_FONT_SIZE;
    private String fontName = "Verdana";
    private double CurrentPosX;
    private double CurrentPosY;


    public FastLinkedList(){
        this.sentinal = new Node(null, null, null);
        this.sentinal.pre = sentinal;
        this.sentinal.next = sentinal;
        this.currentNode = new Node(null, null, null);
        this.size = 0;
        CurrentPosX = MARGIN;
        CurrentPosY = 0;
    }

    public double getCurrentPosX() {
        return CurrentPosX;
    }

    public double getCurrentPosY(){
        return CurrentPosY;
    }

    public Node getStartNode(){
        return sentinal.next;
    }

    public Node getCurrentNode(){
        return currentNode;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    void add(String CharTyped){
        Node newNode = new Node(null, null, null);
        newNode.nodeText = new Text (INITIAL_TEXT_POSITION_X + MARGIN, INITIAL_TEXT_POSITION_Y, CharTyped);
        newNode.nodeText.setFont(Font.font(fontName, fontSize));
        if (isEmpty()){
            sentinal.next = newNode;
            newNode.pre = newNode;
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

    void XYPosUpdate(){
        double TextWidth = 0;
        double TextHeight = 0;
        CurrentPosX = MARGIN;
        CurrentPosY = 0;
        if (!this.isEmpty()){
            Node starter = this.getStartNode();
            while(starter!=null){
                // If starter is the first node
                if (starter.pre == starter){
                    starter.nodeText.setTextOrigin(VPos.TOP);
                    starter.nodeText.setX(getCurrentPosX());
                    starter.nodeText.setY(getCurrentPosY());
                    TextHeight = starter.nodeText.getLayoutBounds().getHeight();
                    TextWidth = starter.nodeText.getLayoutBounds().getWidth();
                    //CurrentPosY = TextHeight;
                    CurrentPosX += TextWidth;
                    starter = starter.next;
                }else{     // If starter is not the first node
                    starter.nodeText.setTextOrigin(VPos.TOP);
                    // If the starter starts a new line
                    if (starter.pre.nodeText.getText().equals("\n") || starter.pre.nodeText.getText().equals("\r") ){
                        CurrentPosY += TextHeight;
                        CurrentPosX = MARGIN;
                        starter.nodeText.setTextOrigin(VPos.TOP);
                        starter.nodeText.setX(getCurrentPosX());
                        starter.nodeText.setY(getCurrentPosY());
                        TextWidth = starter.nodeText.getLayoutBounds().getWidth();
                        CurrentPosX += TextWidth;
                        starter = starter.next;
                    }else{
                        starter.nodeText.setTextOrigin(VPos.TOP);
                        starter.nodeText.setX(getCurrentPosX());
                        starter.nodeText.setY(getCurrentPosY());
                        TextWidth = starter.nodeText.getLayoutBounds().getWidth();
                        CurrentPosX += TextWidth;
                        starter = starter.next;
                    }
                }
            }


        }
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
