import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class BoardTest {

    @Test
    public void stringBoardOf8() {
        // given:
        Board board = new Board(TestSetHelper.initTiles(3));

        // when:
        String stringBoard = board.toString();

        // then:
        assertEquals("3\n0 1 2 \n3 4 5 \n6 7 8 \n", stringBoard);
    }

    @Test
    public void stringBoardOf15() {
        // given:
        Board board = new Board(TestSetHelper.initTiles(4));

        // when:
        String stringBoard = board.toString();

        // then:
        assertEquals("4\n0 1 2 3 \n4 5 6 7 \n8 9 10 11 \n12 13 14 15 \n", stringBoard);
    }

    @Test
    public void equality_OK() {
        // given:
        Board board1 = new Board(TestSetHelper.initTiles(4));
        Board board2 = new Board(TestSetHelper.initTiles(4));

        // when:
        boolean isEqual = board1.equals(board2);

        // then:
        assertTrue(isEqual);
    }

    @Test
    public void equalitySize_NOK() {
        // given:
        Board board1 = new Board(TestSetHelper.initTiles(4));
        Board board2 = new Board(TestSetHelper.initTiles(3));

        // when:
        boolean isEqual = board1.equals(board2);

        // then:
        assertFalse(isEqual);
    }


    @Test
    public void equalityTilesPositions_NOK() {
        // given:
        Board board1 = new Board(TestSetHelper.initTiles(3));
        Board board2 = new Board(TestSetHelper.initTilesWithHamming2());

        // when:
        boolean isEqual = board1.equals(board2);

        // then:
        assertFalse(isEqual);
    }

    @Test
    public void hamming() {
        // given:
        Board board = new Board(TestSetHelper.initTilesWithHamming6());

        // when:
        int hamming = board.hamming();

        // then:
        assertEquals(5, hamming);
    }

    @Test
    public void hamming_Zero() {
        // given:
        Board board = new Board(TestSetHelper.initTilesWithHammingZero());

        // when:
        int hamming = board.hamming();

        // then:
        assertEquals(0, hamming);
    }

    @Test
    public void manhattan() {
        // given:
        Board board = new Board(TestSetHelper.initTilesWithManhattan6());

        // when:
        int manhattan = board.manhattan();

        // then:
        assertEquals(6, manhattan);
    }

    @Test
    public void manhattan_2() {
        // given:
        int[][] tiles = {
                { 1, 2, 3 },
                { 0, 7, 6 },
                { 5, 4, 8 }
        };
        Board board = new Board(tiles);

        // when:
        int manhattan = board.manhattan();

        // then:
        assertEquals(7, manhattan);
    }

    @Test
    public void manhattan_3() {
        // given:
        int[][] tiles = {
                { 0, 1, 3 },
                { 4, 2, 5 },
                { 7, 8, 6 }
        };
        Board board = new Board(tiles);

        // when:
        int manhattan = board.manhattan();

        // then:
        assertEquals(4, manhattan);
    }

    @Test
    public void manhattan_4() {
        // given:
        int[][] tiles = {
                { 5, 8, 7 },
                { 1, 4, 6 },
                { 3, 0, 2 }
        };
        Board board = new Board(tiles);

        // when:
        int manhattan = board.manhattan();

        // then:
        assertEquals(17, manhattan);
    }

    @Test
    public void calculateManhattanOfGoalBoard() {
        // given:
        Board board = new Board(TestSetHelper.initGoalTiles(4));
        assertTrue(board.isGoal());

        // when:
        int manhattan = board.manhattan();

        // then:
        assertEquals(0, manhattan);
    }

    @Test
    public void checkNeighbors() {
        // given:
        Board board = new Board(TestSetHelper.initTilesWithManhattan6());

        // when:
        Iterator<Board> iterator = board.neighbors().iterator();

        // then:
        assertEquals(new Board(TestSetHelper.initTilesNeighborWithManhattan6_1()), iterator.next());
        assertEquals(new Board(TestSetHelper.initTilesNeighborWithManhattan6_2()), iterator.next());
    }

    @Test
    public void check4NeighborsOfBoard() {
        // given:
        Board board = new Board(TestSetHelper.initTilesHaving4Neighbors());

        // when:
        Iterator<Board> iterator = board.neighbors().iterator();

        // then:
        assertEquals(new Board(TestSetHelper.initTilesHaving4Neighbors_Neighbor1()), iterator.next());
        assertEquals(new Board(TestSetHelper.initTilesHaving4Neighbors_Neighbor2()), iterator.next());
        assertEquals(new Board(TestSetHelper.initTilesHaving4Neighbors_Neighbor3()), iterator.next());
        assertEquals(new Board(TestSetHelper.initTilesHaving4Neighbors_Neighbor4()), iterator.next());
    }

}
