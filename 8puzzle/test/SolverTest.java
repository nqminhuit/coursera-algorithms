import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SolverTest {

    @Test
    public void solveMoves() {
        // given:
        Solver solver = new Solver(new Board(TestSetHelper.initTilesHaving2MovesToComplete()));

        // when:
        int minMoves = solver.moves();

        // then:
        assertEquals(2, minMoves);
    }

    @Test
    public void solveSolution() {
        // given:
        Board inputBoard = new Board(TestSetHelper.initTilesHaving4Neighbors());

        // when:
        Solver solver = new Solver(inputBoard);

        // then:
        assertEquals(24, solver.moves());
        assertTrue(solver.isSolvable());
    }

    @Test
    public void unsolvableBoard() {
        // given:
        Board unsolvableBoard = new Board(TestSetHelper.initTilesUnsolvable());

        // when:
        Solver solver = new Solver(unsolvableBoard);

        // then:
        assertFalse(solver.isSolvable());
    }

}
