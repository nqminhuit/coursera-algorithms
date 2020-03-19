import edu.princeton.cs.algs4.StdIn;

public class UnionFind {

    private int[] ids;
    private int numberComponent;

    public UnionFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        numberComponent = n;

        ids = new int[n];
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
        }
    }

    private void union(int p, int q) {
        int pid = ids[p];
        int qid = ids[q];

        if (pid == qid) {
            return;
        }

        for (int i = 0; i < ids.length; ++i) {
            if (ids[i] == pid) {
                ids[i] = qid;
            }
        }
        numberComponent--;
    }

    private int find(int p) {
        return ids[p];
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionFind uf = new UnionFind(n);
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
