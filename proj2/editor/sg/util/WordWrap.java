package sg.util;

import javafx.geometry.VPos;

public class WordWrap {
    private static double ScanPosX;
    private static double ScanPosY;
    private static final int MARGIN = 5;

    private static double getScanPosX() {
        return ScanPosX;
    }

    private static double getScanPosY(){
        return ScanPosY;
    }

    public static void warp(FastLinkedList l){

        double TextWidth;
        double TextHeight = 0;
        ScanPosX = MARGIN;
        ScanPosY = 0;
        double currentWordLen = 0;
        FastLinkedList.Node wordStart;
        if (!l.isEmpty()){
            FastLinkedList.Node starter = l.getStartNode();
            while(starter!=null){


                // If starter is the first node
                if (starter.pre == l.sentinal){
                    starter.nodeText.setTextOrigin(VPos.TOP);
                    starter.nodeText.setX(getScanPosX());
                    starter.nodeText.setY(getScanPosY());
                    TextHeight = Math.round(l.sentinal.nodeText.getLayoutBounds().getHeight());
                    TextWidth = Math.round(starter.nodeText.getLayoutBounds().getWidth());
                    //CurrentHeight = TextHeight;
                    ScanPosX += TextWidth;
                    if (starter.nodeText.getText().equals(" ")){
                        currentWordLen = 0;
                        wordStart = starter.next;
                    }else{
                        currentWordLen += TextWidth;
                    }
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
                        currentWordLen = 0;
                        wordStart = starter.next;
                        starter = starter.next;
                    }else{
                        starter.nodeText.setTextOrigin(VPos.TOP);
                        starter.nodeText.setX(getScanPosX());
                        starter.nodeText.setY(getScanPosY());
                        TextWidth = Math.round(starter.nodeText.getLayoutBounds().getWidth());
                        ScanPosX += TextWidth;
                        if (starter.nodeText.getText().equals(" ")){
                            currentWordLen = 0;
                            wordStart = starter.next;
                        }else{
                            currentWordLen += TextWidth;
                        }
                        starter = starter.next;
                    }
                }



            }


        }
    }
}
