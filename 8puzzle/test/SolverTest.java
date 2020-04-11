import static org.junit.Assert.assertEquals;
import java.util.Iterator;
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
        Solver solver = new Solver(new Board(TestSetHelper.initTilesHaving4Neighbors()));

        // when:
        Iterator<Board> iterator = solver.solution().iterator();

        // then:
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        // assertEquals(null, iterator.next());
        // assertEquals(null, iterator.next());
        // assertEquals(null, iterator.next());
        // assertEquals(null, iterator.next());

    }

}
