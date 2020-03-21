import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;

public class WeightedQuickUnionUF {

    private static int[] ids;

    private int[] ranks;

    private int numberComponent;

    public WeightedQuickUnionUF(int n) {
        numberComponent = n;
        ids = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
            ranks[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        if (rootP == rootQ) {
            return;
        }

        if (ranks[rootP] < ranks[rootQ]) {
            ids[rootP] = rootQ;
            ranks[rootQ] += ranks[rootP];
        } else {
            ids[rootQ] = rootP;
            ranks[rootP] += ranks[rootQ];
        }

        numberComponent--;
    }

    private int getRoot(int p) {
        if (ids[p] == p) {
            return p;
        }

        ids[p] = ids[ids[p]]; // path compression
        return getRoot(ids[p]);
        // return ids[p] == p ? p : getRoot(ids[p]);
    }

    public int find(int p) {
        return getRoot(p);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        Arrays.stream(ids).forEach(id -> System.out.print(id + " "));
        System.out.println();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.find(p) == uf.find(q)) {
                continue;
            }

            uf.union(p, q);
            Arrays.stream(ids).forEach(id -> System.out.print(id + " "));
            System.out.println();
        }

        System.out.println("result: " + uf.numberComponent + " components");
    }

}
