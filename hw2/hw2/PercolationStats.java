package hw2;                       
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;


public class PercolationStats {

    private double T;
    private double[] PercThreshold;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        if (N <= 0 || T <= 0){
            throw new java.lang.IllegalArgumentException("N and T should be positive integers!");
        }
        this.T = (double) T;
        PercThreshold = new double[T];
        for (int i = 0; i < T; i++){
            Percolation perc = new Percolation(N);
            while(!perc.percolates()){
                int RandomSiteIndex = StdRandom.uniform(N*N);
                if (!perc.isOpen(OneDToX(RandomSiteIndex, N), OneDToY(RandomSiteIndex, N))){
                    perc.open(OneDToX(RandomSiteIndex, N), OneDToY(RandomSiteIndex, N));
                }
            }
            //double a1 = (double) perc.numberOfOpenSites();
            //double b1 = (double) N*N;
            PercThreshold[i] = ((double) perc.numberOfOpenSites()) / ((double) N*N);
        }
    }

    private int OneDToX(int index, int N){
        return (index / N);
    }

    private int OneDToY(int index, int N){
        return index % N;
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(PercThreshold);
    }



    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(PercThreshold);
    }


    // low  endpoint of 95% confidence interval
    public double confidenceLow(){
        return (mean() - 1.96*stddev()/Math.sqrt(T));
    }



    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return (mean() + 1.96*stddev()/Math.sqrt(T));
    }


    public static void main(String[] args)
    {
        int N = new Integer(args[0]);
        int T = new Integer(args[1]);
        Stopwatch timer = new Stopwatch();
        PercolationStats stats = new PercolationStats(N,T);
        double time = timer.elapsedTime();
        System.out.println("elapsed time:\t\t\t = " + time + " seconds");
        System.out.println("mean:\t\t\t\t = " + stats.mean());
        System.out.println("stddev:\t\t\t\t = " + stats.stddev());
        System.out.println("95% confidence interval:\t = " + stats.confidenceLow() + ", " + stats.confidenceHigh());
    }
}
