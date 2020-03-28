import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double FACTOR = 1.96D;
    private final double[] a;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials < 1 || n < 1) {
            throw new IllegalArgumentException();
        }
        a = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            a[i] = 1D * p.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(a);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - FACTOR * stddev() / Math.sqrt(a.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + FACTOR * stddev() / Math.sqrt(a.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[0]);
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out
            .println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }

}
