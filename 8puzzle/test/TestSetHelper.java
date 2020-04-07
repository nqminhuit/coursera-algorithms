
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

    public static int[][] initTilesWithHamming6() {
        int[][] tiles = new int[3][3];

        tiles[0][0] = 8;
        tiles[0][1] = 2;
        tiles[0][2] = 3;

        tiles[1][0] = 4;
        tiles[1][1] = 1;
        tiles[1][2] = 7;

        tiles[2][0] = 0;
        tiles[2][1] = 5;
        tiles[2][2] = 6;

        return tiles;
    }

    public static int[][] initTilesWithHamming2() {
        int[][] tiles = new int[3][3];

        tiles[0][0] = 1;
        tiles[0][1] = 2;
        tiles[0][2] = 3;

        tiles[1][0] = 4;
        tiles[1][1] = 5;
        tiles[1][2] = 7;

        tiles[2][0] = 6;
        tiles[2][1] = 8;
        tiles[2][2] = 0;

        return tiles;
    }

    public static int[][] initTilesWithHammingZero() {
        int[][] tiles = new int[3][3];

        tiles[0][0] = 1;
        tiles[0][1] = 2;
        tiles[0][2] = 3;

        tiles[1][0] = 4;
        tiles[1][1] = 5;
        tiles[1][2] = 6;

        tiles[2][0] = 7;
        tiles[2][1] = 8;
        tiles[2][2] = 0;

        return tiles;
    }

}
