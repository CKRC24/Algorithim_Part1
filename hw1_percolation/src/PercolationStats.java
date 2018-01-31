import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double average = 0;
    private double std = 0;
    private int gridSize;
    private int num_trials;
    private double confidence_constant = 1.96;

    // constructor
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n <= 0 or trials <= 0");
        this.gridSize = n;
        this.num_trials = trials;
        double[] threshold_List = new double[this.num_trials];

        // perform trials independent experiments on an n-by-n grid
        for (int i=0; i<this.num_trials; i++) {
            Percolation percolation = new Percolation(n);

            while(!percolation.percolates()){
                // random select block site to open
                int row = StdRandom.uniform(1, this.gridSize+1);
                int col = StdRandom.uniform(1, this.gridSize+1);
                if(!percolation.isOpen(row, col)){
                    percolation.open(row, col);
                }
            }
            double threshold = percolation.numberOfOpenSites()/Math.pow(n,2);
            threshold_List[i] = threshold;
        }

        // calculate mean std
        this.average = StdStats.mean(threshold_List);
        this.std = StdStats.stddev(threshold_List);
    }

    // calculate mean
    public double mean(){
        return this.average;
    }

    // calculate std
    public double stddev(){
        return this.std;
    }

    // calculate low endpoint of 95% confidence interval
    public double confidenceLo(){
        return (this.average - (this.confidence_constant * this.std)/Math.sqrt((double) this.num_trials));
    }

    // calculate high endpoint of 95% confidence interval
    public double confidenceHi(){
        return (this.average + (this.confidence_constant * this.std)/Math.sqrt((double) this.num_trials));
    }

    public static void main(String[] args) {
        PercolationStats p_stats = new PercolationStats(200, 100);
        double average = p_stats.mean();
        double std = p_stats.stddev();
        double conf_Hi = p_stats.confidenceHi();
        double conf_Lo = p_stats.confidenceLo();

        // print mean, std, conf_Hi, conf_Lo
        System.out.format("mean                     = %f%n", average);
        System.out.format("stddev                   = %f%n", std);
        System.out.format("95%% confidence interval = [%f, %f]%n", conf_Lo, conf_Hi);
    }
}
