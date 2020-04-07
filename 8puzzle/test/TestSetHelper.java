
public class TestSetHelper {

    public static int[][] initTiles(int n) {
        int[][] tiles = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = i * n + j;
            }
        }

        return tiles;
    }

}
