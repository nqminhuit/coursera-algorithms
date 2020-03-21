import edu.princeton.cs.algs4.StdIn;

public class QuickUnionUF {

    private int[] ids;

    private int numberComponent;

    public QuickUnionUF(int n) {
        numberComponent = n;
        ids = new int[n];
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        if (rootP == rootQ) {
            return;
        }

        ids[rootP] = rootQ;

        numberComponent--;
    }

    private int getRoot(int p) {
        return ids[p] == p ? p : getRoot(ids[p]);
    }

    public int find(int p) {
        return getRoot(p);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.find(p) == uf.find(q)) {
                continue;
            }

            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println("result: " + uf.numberComponent + " components");
    }

}
