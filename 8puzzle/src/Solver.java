import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private List<Board> solutions;

    private final int totalMoves;

    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board inputBoard) {
        if (inputBoard == null) {
            throw new IllegalArgumentException();
        }

        SearchNode node = new SearchNode(inputBoard, 0, null, inputBoard.manhattan());

        MinPQ<SearchNode> minPq = new MinPQ<>();
        minPq.insert(node);

        while (true) {
            node = minPq.delMin();
            if (node.manhattanDistance == 0) {
                solvable = true;
                break;
            } else if (node.board.twin().isGoal()) {
                solvable = false;
                totalMoves = -1;
                return;
            }

            for (Board neighborBoard : node.board.neighbors()) {
                if (node.prev != null && neighborBoard.equals(node.prev.board)) {
                    continue;
                }
                SearchNode neighborNode =
                    new SearchNode(neighborBoard, node.moves + 1, node, neighborBoard.manhattan());
                minPq.insert(neighborNode);
            }
        }

        totalMoves = node.moves;

        generateSolutionSteps(node);
    }

    private void generateSolutionSteps(SearchNode node) {
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
        return this.totalMoves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return this.solutions;
    }

    private class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final int moves;
        private final SearchNode prev;
        private final int manhattanDistance;

        public SearchNode(Board board, int moves, SearchNode prev, int manhattan) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.manhattanDistance = manhattan;
        }

        @Override
        public int compareTo(SearchNode that) {
            int thisPriority = this.manhattanDistance + this.moves;
            int thatPriority = that.manhattanDistance + that.moves;
            return thisPriority - thatPriority;
        }
    }

}
