import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int VIRTUAL_TOP = 0;

    private boolean[][] openSites;

    private final WeightedQuickUnionUF wquf;

    private final WeightedQuickUnionUF wqufFullCheck;

    private int numberOfOpenSites = 0;

    private final int virtualBot;

    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        openSites = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                openSites[i][j] = false;
            }
        }
        wquf = new WeightedQuickUnionUF((n + 1) * (n + 1) + 2);
        wqufFullCheck = new WeightedQuickUnionUF((n + 1) * (n + 1) + 1);
        virtualBot = n * n + 1;
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
        if (row > 1 && isOpen(row - 1, col)) {
            wquf.union(toBeOpenedSite, getIndex(row - 1, col));
            wqufFullCheck.union(toBeOpenedSite, getIndex(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            wquf.union(toBeOpenedSite, getIndex(row + 1, col));
            wqufFullCheck.union(toBeOpenedSite, getIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            wquf.union(toBeOpenedSite, getIndex(row, col - 1));
            wqufFullCheck.union(toBeOpenedSite, getIndex(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            wquf.union(toBeOpenedSite, getIndex(row, col + 1));
            wqufFullCheck.union(toBeOpenedSite, getIndex(row, col + 1));
        }

        if (row == 1) {
            wquf.union(toBeOpenedSite, VIRTUAL_TOP);
            wqufFullCheck.union(toBeOpenedSite, VIRTUAL_TOP);
        } else if (row == n) {
            wquf.union(toBeOpenedSite, virtualBot);
        }

        openSites[row][col] = true;
        numberOfOpenSites++;
    }

    private void validateOutsideOfRange(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException("row/col outside of range!");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateOutsideOfRange(row, col);
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            return wqufFullCheck.find(getIndex(row, col)) == wqufFullCheck.find(VIRTUAL_TOP);
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.find(VIRTUAL_TOP) == wquf.find(virtualBot);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation p = new Percolation(n);
        while (!p.percolates()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            p.open(row, col);
        }

        double pStar = 1D * p.numberOfOpenSites() / (n * n);
        System.out.println("open sites = " + p.numberOfOpenSites());
        System.out.println("p* = " + pStar);
    }

}
