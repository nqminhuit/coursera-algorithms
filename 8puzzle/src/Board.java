import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int n;

    private final int[][] tiles;

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
                while (n * row + col + 1 != tiles[i][j]) {
                    if (n * row + col + 1 > tiles[i][j]) {
                        if (n * row >= tiles[i][j]) {
                            row--; // move up
                        } else {
                            col--; // move left
                        }
                    } else {
                        if (n * (row + 1) < tiles[i][j]) {
                            row++; // move down
                        } else {
                            col++; // move right
                        }
                    }
                    distance++;
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
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

    private int[][] copyTiles() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    private void swap(int[][] toSwap, int row, int col, int swapRow, int swapCol) {
        int temp = toSwap[swapRow][swapCol];
        toSwap[swapRow][swapCol] = toSwap[row][col];
        toSwap[row][col] = temp;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();

        int row = 0;
        int col = 0;
        outer_loop: for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] == 0) {
                    row = i;
                    col = j;
                    break outer_loop;
                }
            }
        }

        if (row - 1 >= 0) {
            int[][] newTiles = copyTiles();
            swap(newTiles, row, col, row - 1, col);
            Board neighbor = new Board(newTiles);
            neighbors.add(neighbor);
        }

        if (row + 1 < n) {
            int[][] newTiles = copyTiles();
            swap(newTiles, row, col, row + 1, col);
            Board neighbor = new Board(newTiles);
            neighbors.add(neighbor);
        }

        if (col - 1 >= 0) {
            int[][] newTiles = copyTiles();
            swap(newTiles, row, col, row, col - 1);
            Board neighbor = new Board(newTiles);
            neighbors.add(neighbor);
        }

        if (col + 1 < n) {
            int[][] newTiles = copyTiles();
            swap(newTiles, row, col, row, col + 1);
            Board neighbor = new Board(newTiles);
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[] flatTiles = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                flatTiles[idx++] = tiles[i][j];
            }
        }

        for (int i = 0; i < flatTiles.length; ++i) {
            if (flatTiles[i] != 0) {
                if (flatTiles[i + 1] != 0) {
                    swap(flatTiles, i, i + 1);
                    break;
                }
            }
        }

        idx = 0;
        int[][] twin = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                twin[i][j] = flatTiles[idx++];
            }
        }

        return new Board(twin);
    }

}
