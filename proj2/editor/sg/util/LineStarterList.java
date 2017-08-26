package sg.util;


public class LineStarterList {
    StarterNode CursorLineStarter;
    StarterNode LineStarter;
    StarterNode sentinal;
    private double ClickingPosX;
    private double ClickingPosY;
    private double FontHeight;
    int TotalLine = 0;
    int CursortLine = 0;


    void addStarter(FastLinkedList.Node newStarter){

    }

    void changeStarter(){

    }

    void deleteStarter(){

    }

    void setStarterContent(StarterNode toSetStarter, FastLinkedList.Node node){
        toSetStarter.StarterContent = node;
    }

    void  getClickingStarter(){
        int ClickingLineNo = (int) Math.ceil(ClickingPosY/FontHeight);
        if (ClickingLineNo >= TotalLine){
            CursorLineStarter = sentinal.next.prev;
        }
        if (ClickingLineNo <= 1){
            CursorLineStarter = sentinal.next;
        }
        StarterNode tempNode = sentinal.next;
        for (int i = 1; i < ClickingLineNo; i++){
            tempNode = tempNode.next;
        }
        CursorLineStarter = tempNode;
    }

    class StarterNode{
        StarterNode prev,next;
        FastLinkedList.Node StarterContent;
    }
}
