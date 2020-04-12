import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private SearchNode node;

    private List<Board> solutions;

    private int totalMoves;

    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board inputBoard) {
        if (inputBoard == null) {
            throw new IllegalArgumentException();
        }

        if (inputBoard.hamming() % 2 != 0) {
            solvable = false;
            totalMoves = 0;
            return;
        }

        node = new SearchNode(inputBoard);

        MinPQ<SearchNode> minPq = new MinPQ<>();
        minPq.insert(node);

        int manhattan;
        while (true) {
            node = minPq.delMin();
            if (node.board.isGoal()) {
                solvable = true;
                break;
            }

            manhattan = node.board.manhattan();
            for (Board neighborBoard : node.board.neighbors()) {
                SearchNode neighborNode = new SearchNode(neighborBoard, node.moves + 1, node, manhattan);
                if (node.prev != null && neighborNode.board.equals(node.prev.board)) {
                    continue;
                }
                minPq.insert(neighborNode);
            }

            if (minPq.isEmpty()) {
                solvable = false;
                break;
            }
        }

        totalMoves = node.moves;

        generateSolutionSteps();
    }

    private void generateSolutionSteps() {
        Stack<Board> stepsToGoalBoard = new Stack<>();
        while (node != null) {
            stepsToGoalBoard.push(node.board);
            node = node.prev;
        }
        solutions = new ArrayList<>();
        while (!stepsToGoalBoard.isEmpty()) {
            solutions.add(stepsToGoalBoard.pop());
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return totalMoves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return solutions;
    }

    private class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final int moves;
        private final SearchNode prev;
        private final int manhattanDistance;

        public SearchNode(Board board) {
            this.board = board;
            moves = 0;
            prev = null;
            manhattanDistance = 0;
        }

        public SearchNode(Board board, int moves, SearchNode prev, int manhattan) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.manhattanDistance = manhattan;
        }

        @Override
        public int compareTo(SearchNode that) {
            int thisPriority = this.manhattanDistance + this.moves;
            int thatPriority = that.board.manhattan() + that.moves;
            return thisPriority - thatPriority;
        }
    }

}
