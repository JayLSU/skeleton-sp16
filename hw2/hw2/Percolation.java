package hw2;                       
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private WeightedQuickUnionUF sites;
    private int VirtualTopIndex;
    private int VirtualBottomIndex;
    private boolean openState[];

    public Percolation(int N){
        if(N<=0) throw new IllegalArgumentException("N cannot be less than or equal to 0.");
        this.N = N;
        this.sites = new WeightedQuickUnionUF(N * N + 2); // All sites on the grid + 2 virtual sites
        this.VirtualTopIndex = N * N ; // Virtual top site index
        this.VirtualBottomIndex = N * N + 1; // Virtual bottom site index
        this.openState = new boolean[N * N];
    }

    public void open(int row, int col){
        if (isOpen(row, col)){
            return;
        }

        openState[xyTo1D(row, col)] = true;

        connectWithOpenNeighbour(row, col);
    }

    private int xyTo1D(int row, int col){
        if (validateXY(row, col)){
            return (int)(N*(row) + col);
        }
        throw new IndexOutOfBoundsException("Invalid indices input!");
    }

    private void connectWithOpenNeighbour(int row, int col){
        if (row == 0) { sites.union(xyTo1D(row, col), VirtualTopIndex); } // First row open sites always connect with virtual top site

        if (row == this.N-1) {sites.union(xyTo1D(row, col), VirtualBottomIndex);} // Bottom row open sites always connect with virtual bottom site

        if (validateXY(row - 1, col) && isOpen(row - 1, col)){
            sites.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        }

        if (validateXY(row + 1, col) && isOpen(row + 1, col)){
            sites.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        }

        if (validateXY(row, col - 1) && isOpen(row, col - 1)){
            sites.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        }

        if (validateXY(row, col + 1) && isOpen(row, col + 1)){
            sites.union(xyTo1D(row, col + 1), xyTo1D(row, col));
        }

    }

    private boolean validateXY(int row, int col){
        return (row >= 0 && col >= 0 && row < N && col < N);
    }

    public boolean isOpen(int row, int col){
        return openState[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col){
        return sites.connected(xyTo1D(row, col), VirtualTopIndex);
    }

    public int numberOfOpenSites(){
        int num = 0;
        for (int i = 0; i < openState.length; i++){
            if (openState[i]){
                num++;
            }
        }
        return num;
    }

    public boolean percolates(){
        return sites.connected(VirtualTopIndex, VirtualBottomIndex);
    }
    /***
    public static void main(String[] args){

    }*/
}                       
