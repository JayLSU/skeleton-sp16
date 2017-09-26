package sg.util;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FastLinkedList {
    public Node sentinal;
    private Node currentNode;
    private int size;
    private static final int INITIAL_TEXT_POSITION_X = 0;
    private static final int INITIAL_TEXT_POSITION_Y = 0;
    private static final int MARGIN = 5;
    private static final int STARTING_FONT_SIZE = 20;
    private int fontSize = STARTING_FONT_SIZE;
    private String fontName = "Verdana";
    private double CurrentPosX;
    double CurrentPosY;
    private double ScanPosX;
    private double ScanPosY;
    private static final int WINDOW_WIDTH = 500;
    private int lineWidth;
    //private double CurrentHeight;
    private Text SentinalText = new Text(MARGIN, 0, "");

    public FastLinkedList(){
        this.sentinal = new Node(null, null, SentinalText);
        this.sentinal.nodeText.setFont(Font.font(fontName, fontSize));
        this.sentinal.pre = sentinal;
        this.sentinal.next = sentinal;
        this.currentNode = new Node(null, null, null);
        this.size = 0;
        CurrentPosX = MARGIN;
        CurrentPosY = 0;
        lineWidth = WINDOW_WIDTH;
        //CurrentHeight = 24;
    }

/*    void setCurPosX(double x){CurrentPosX = x;}

    void setCurPosY(double y){CurrentPosY = y;}*/

    void setCurNode(FastLinkedList.Node n){currentNode = n;}

    /*private double getScanPosX() {
        return ScanPosX;
    }

    private double getScanPosY(){
        return ScanPosY;
    }*/

    Node getStartNode(){
        return sentinal.next;
    }

    public Node getCurrentNode(){
        return currentNode;
    }

    public double getCurrentPosX() {
        return CurrentPosX;
    }

    public double getCurrentPosY() {
        return CurrentPosY;
    }

    public void CurrentPosUpdate(){
        if (this.isEmpty()){
            CurrentPosX = MARGIN;
            CurrentPosY = 0;
        }else if (!currentNode.nodeText.getText().equals("\n")){
            CurrentPosX = currentNode.nodeText.getX() + Math.round(currentNode.nodeText.getLayoutBounds().getWidth());
            CurrentPosY = currentNode.nodeText.getY();
        }else{
            CurrentPosX = MARGIN;
            double deltaH = Math.round(sentinal.nodeText.getLayoutBounds().getHeight());
            CurrentPosY = currentNode.nodeText.getY();
            CurrentPosY += deltaH;
        }

    }

    public void setLineWidth(int L){
        lineWidth = L;
    }

    public int getLineWidth(){return lineWidth;}

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void add(String CharTyped){
        Node newNode = new Node(null, null, null);
        newNode.nodeText = new Text (INITIAL_TEXT_POSITION_X + MARGIN, INITIAL_TEXT_POSITION_Y, CharTyped);
        newNode.nodeText.setFont(Font.font(fontName, fontSize));
        if (isEmpty()){
            sentinal.next = newNode;
            newNode.pre = sentinal;
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
    double getCursorHeight(){return Math.round(sentinal.nodeText.getLayoutBounds().getHeight());}

    /*void XYPosUpdate(){
        double TextWidth;
        double TextHeight = 0;
        ScanPosX = MARGIN;
        ScanPosY = 0;
        if (!this.isEmpty()){
            Node starter = this.getStartNode();
            while(starter!=null){
                // If starter is the first node
                if (starter.pre == sentinal){
                    starter.nodeText.setTextOrigin(VPos.TOP);
                    starter.nodeText.setX(getScanPosX());
                    starter.nodeText.setY(getScanPosY());
                    TextHeight = Math.round(sentinal.nodeText.getLayoutBounds().getHeight());
                    TextWidth = Math.round(starter.nodeText.getLayoutBounds().getWidth());
                    //CurrentHeight = TextHeight;
                    ScanPosX += TextWidth;
                    starter = starter.next;
                }else{     // If starter is not the first node
                    starter.nodeText.setTextOrigin(VPos.TOP);
                    // If the starter starts a new line
                    if (starter.pre.nodeText.getText().equals("\n")){
                        ScanPosY += TextHeight;
                        ScanPosX = MARGIN;
                        starter.nodeText.setTextOrigin(VPos.TOP);
                        starter.nodeText.setX(getScanPosX());
                        starter.nodeText.setY(getScanPosY());
                        TextWidth = Math.round(starter.nodeText.getLayoutBounds().getWidth());
                        ScanPosX += TextWidth;
                        starter = starter.next;
                    }else{
                        starter.nodeText.setTextOrigin(VPos.TOP);
                        starter.nodeText.setX(getScanPosX());
                        starter.nodeText.setY(getScanPosY());
                        TextWidth = Math.round(starter.nodeText.getLayoutBounds().getWidth());
                        ScanPosX += TextWidth;
                        starter = starter.next;
                    }
                }
            }


        }
    }*/

    void delete(){
        if (!isEmpty()){
            if (currentNode.next!=null){
                currentNode.next.pre = currentNode.pre;
            }
            currentNode.pre.next = currentNode.next;
            currentNode.nodeText.setX(MARGIN);
            currentNode.nodeText.setY(0);
            currentNode.nodeText.setText("");
            if (currentNode!=sentinal){
                size -= 1;
            }
            currentNode = currentNode.pre;

        }
    }

    void fontUpdate(int font){
        if (!this.isEmpty()){
            Node tempNode = sentinal;
            while(tempNode!=null){
                fontSize = font;
                tempNode.nodeText.setFont(Font.font(fontName,fontSize));
                tempNode = tempNode.next;
            }
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
