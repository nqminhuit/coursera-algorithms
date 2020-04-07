import static org.junit.Assert.assertEquals;
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
}
