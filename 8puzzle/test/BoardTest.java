import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
}
