import java.util.ArrayList;
import java.util.List;

public class Solver {

    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        moves = 0;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        List<Board> solutions = new ArrayList<>();
        return solutions;
    }

}
