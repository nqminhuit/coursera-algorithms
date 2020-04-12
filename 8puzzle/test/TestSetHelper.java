
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
        int[][] tiles = {
                { 8, 2, 3 },
                { 4, 1, 7 },
                { 0, 5, 6 }
        };
        return tiles;
    }

    public static int[][] initTilesWithHamming2() {
        int[][] tiles = {
                { 1, 2, 3 },
                { 4, 5, 7 },
                { 6, 8, 0 }
        };
        return tiles;
    }

    public static int[][] initTilesWithHammingZero() {
        int[][] tiles = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };
        return tiles;
    }

    public static int[][] initTilesWithManhattan6() {
        int[][] tiles = {
                { 8, 2, 3 },
                { 4, 5, 6 },
                { 7, 1, 0 }
        };
        return tiles;
    }

    public static int[][] initTilesNeighborWithManhattan6_1() {
        int[][] tiles = {
                { 8, 2, 3 },
                { 4, 5, 0 },
                { 7, 1, 6 }
        };
        return tiles;
    }

    public static int[][] initTilesNeighborWithManhattan6_2() {
        int[][] tiles = {
                { 8, 2, 3 },
                { 4, 5, 6 },
                { 7, 0, 1 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving4Neighbors() {
        int[][] tiles = {
                { 8, 3, 2 },
                { 5, 0, 1 },
                { 4, 6, 7 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving4Neighbors_Neighbor1() {
        int[][] tiles = {
                { 8, 0, 2 },
                { 5, 3, 1 },
                { 4, 6, 7 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving4Neighbors_Neighbor2() {
        int[][] tiles = {
                { 8, 3, 2 },
                { 5, 6, 1 },
                { 4, 0, 7 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving4Neighbors_Neighbor3() {
        int[][] tiles = {
                { 8, 3, 2 },
                { 0, 5, 1 },
                { 4, 6, 7 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving4Neighbors_Neighbor4() {
        int[][] tiles = {
                { 8, 3, 2 },
                { 5, 1, 0 },
                { 4, 6, 7 }
        };
        return tiles;
    }

    public static int[][] initTilesHaving2MovesToComplete() {
        int[][] tiles = {
                { 1, 2, 3 },
                { 4, 0, 6 },
                { 7, 5, 8 }
        };

        return tiles;
    }

    public static int[][] initTilesUnsolvable() {
        int[][] tiles = {
                { 8, 6, 7 },
                { 2, 5, 4 },
                { 1, 3, 0 }
        };
        return tiles;
    }

    public static int[][] initGoalTiles(int n) {
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = n * i + j + 1;
            }
        }
        return tiles;
    }

}
