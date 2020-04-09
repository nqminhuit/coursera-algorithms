public class Board {

    private int n;

    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tilesInput) {
        n = tilesInput.length;
        tiles = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = tilesInput[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append("\n");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sb.append(tiles[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        int ham = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == n - 1 && j == n - 1) {
                    if (tiles[i][j] != 0) {
                        return ham;
                    }
                } else if (tiles[i][j] != 1 + i * n + j) {
                    ham++;
                }
            }
        }

        return ham;
    }

    private int decreaseSteps(int row, int col, int tile) {
        int distance = 0;
        while (n * row + col + 1 > tile) {
            if (n * row > tile) {
                row--; // move up
            } else {
                col--; // move left
            }
            distance++;
        }
        return distance;
    }

    private int increaseSteps(int row, int col, int tile) {
        int distance = 0;
        while (n * row + col + 1 < tile) { // increase
            if (n * (row + 1) < tile) {
                row++; // move down
            } else {
                col++; // move right
            }
            distance++;
        }
        return distance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (n * i + j + 1 == tiles[i][j] || tiles[i][j] == 0) {
                    continue;
                }
                int row = i;
                int col = j;
                if (n * row + col + 1 > tiles[i][j]) {
                    distance += decreaseSteps(row, col, tiles[i][j]);
                } else {
                    distance += increaseSteps(row, col, tiles[i][j]);
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        Board that = (Board) y;
        if (this.n != that.n) {
            return false;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

}
