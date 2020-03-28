import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] openSites;

    private final WeightedQuickUnionUF wquf;

    private int numberOfOpenSites = 0;

    private final int virtualBot;

    private final int virtualTop;

    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        openSites = new boolean[n][n];
        wquf = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n;
        virtualBot = n + 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                openSites[i][j] = false;
            }
        }
    }

    private int getIndex(int row, int col) {
        return n * row + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        int toBeOpenedSite = getIndex(row, col);
        if (isOpen(row - 1, col)) {
            wquf.union(toBeOpenedSite, getIndex(row - 1, col));
        }
        if (isOpen(row + 1, col)) {
            wquf.union(toBeOpenedSite, getIndex(row + 1, col));
        }
        if (isOpen(row, col - 1)) {
            wquf.union(toBeOpenedSite, getIndex(row, col - 1));
        }
        if (isOpen(row, col + 1)) {
            wquf.union(toBeOpenedSite, getIndex(row, col + 1));
        }

        if (row == 0) {
            wquf.union(toBeOpenedSite, virtualTop);
        } else if (row == n - 1) {
            wquf.union(toBeOpenedSite, virtualBot);
        }

        openSites[row][col] = true;
        numberOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= n || col >= n || row < 0 || col < 0) {
            return false;
        }
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return wquf.find(getIndex(row, col)) == wquf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.find(virtualTop) == wquf.find(virtualBot);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row;
            int col;
            row = StdIn.readInt() - 1;
            col = StdIn.readInt() - 1;
            p.open(row, col);
        }

        double pStar = 1D * p.numberOfOpenSites() / (n * n);
        System.out.println("open sites = " + p.numberOfOpenSites());
        System.out.println("p* = " + pStar);
    }

}
