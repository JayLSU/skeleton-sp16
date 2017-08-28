package sg.util;


class LineStarterArray<Blorp> {

/*    double ClickingPosX;
    double ClickingPosY;
    double FontHeight;*/
    Blorp[] LineStarters;
    private int TotalLine;
    private int back;
    private int resizeFactor;
    private double usageFactor;

    LineStarterArray(){
/*        ClickingPosX = 0.0;
        ClickingPosY = 0.0;*/
        LineStarters = (Blorp []) new Object[20];
        TotalLine = 0;
        back = 0;
        resizeFactor = 2;
        usageFactor = 0.25;
    }



/*    void setFontHeight(double fontheight){
        FontHeight = fontheight;
    }*/


    private int addOne(int index){
        int reminder = (index + 1);
        if (reminder >= LineStarters.length) {
            reminder = reminder - LineStarters.length;
        }
        return reminder;
    }

    private int minusOne(int index){
        int reminder = (index - 1);
        if (reminder < 0) {
            reminder = reminder + LineStarters.length;
        }
        return reminder;
    }

    boolean isEmpty(){
        if (TotalLine == 0) {
            return true;
        }
        return false;
    }

    boolean isFull(){
        if (TotalLine == LineStarters.length){
            return true;
        }
        return false;
    }


    private void doublesize(){
        int newsize = resizeFactor * TotalLine;
        Blorp[] temp = (Blorp []) new Object[newsize];
        System.arraycopy(LineStarters,0,temp,0,TotalLine);
        LineStarters = temp;
        back = TotalLine;

    }

    private void halfsize(){
        int newsize = LineStarters.length / resizeFactor;
        Blorp[] temp = (Blorp[]) new Object[newsize];
        for(int i=0;i <= this.TotalLine();i++){
            temp[i] = this.LineStarters[i];
        }
        LineStarters = temp;
        back = TotalLine;
    }

    private void resize(){
        if (isFull()){
            doublesize();
        }
        else{
            halfsize();
        }
    }

    void addBack(Blorp newNode){
        if (this.isFull()){
            this.resize();
        }
        this.LineStarters[back] = newNode;
        this.back = this.addOne(this.back);
        TotalLine += 1;
    }

    Blorp removeBack(){
        double currentUsage = (double) this.TotalLine()/LineStarters.length;
        if (this.isEmpty()){
            return null;
        }
        if (currentUsage <= this.usageFactor && LineStarters.length > 20){
            this.resize();
        }
        TotalLine -= 1;
        this.back = this.minusOne(this.back);
        Blorp removedNode = LineStarters[this.back];
        LineStarters[this.back] = null;
        return removedNode;
    }

    int TotalLine(){
        return this.TotalLine;
    }

    Blorp get(int i){
        return LineStarters[i];
    }

    int getLen(){return LineStarters.length;}

    void setStarterContent(int i, Blorp node){
        LineStarters[i] = node;
    }
/*
    void  SetCursorLineStarter(){
        int ClickingLineNo = (int) Math.floor(ClickingPosY/FontHeight);
        CursorLineStarter = LineStarters[ClickingLineNo];
    }*/

}
