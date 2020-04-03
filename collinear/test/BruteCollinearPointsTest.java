import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class BruteCollinearPointsTest {

    /**
     * @see "test-set/input_12.png"
     */
    private Point[] initPointsWith2Segments() {
        Point[] points = new Point[12];
        points[0] = new Point(2, 1);
        points[1] = new Point(14, 7);
        points[2] = new Point(4, 2);
        points[3] = new Point(8, 4);

        points[4] = new Point(7, 10);
        points[5] = new Point(10, 4);
        points[6] = new Point(9, 6);
        points[7] = new Point(11, 2);

        points[8] = new Point(4, 5);
        points[9] = new Point(9, 3);
        points[10] = new Point(13, 6);
        points[11] = new Point(11, 11);

        StdRandom.shuffle(points);
        return points;
    }

    /**
     * @see "test-set/input_17_no"
     */
    private Point[] initPointsWith0Segments() {
        Point[] points = new Point[17];
        points[0] = new Point(1, 6);
        points[1] = new Point(2, 7);
        points[2] = new Point(3, 5);
        points[3] = new Point(1, 4);
        points[4] = new Point(2, 3);
        points[5] = new Point(6, 4);
        points[6] = new Point(6, 9);
        points[7] = new Point(4, 9);
        points[8] = new Point(14, 10);
        points[9] = new Point(14, 11);
        points[10] = new Point(13, 1);
        points[11] = new Point(15, 1);
        points[12] = new Point(9, 2);
        points[13] = new Point(0, 2);
        points[14] = new Point(11, 0);
        points[15] = new Point(9, 12);
        points[16] = new Point(12, 8);
        StdRandom.shuffle(points);
        return points;
    }

    @Test
    public void foundTwoSegments() {
        // given:
        Point[] points = initPointsWith2Segments();
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        // when:
        LineSegment[] segments = bcp.segments();

        // then:
        assertEquals(2, bcp.numberOfSegments());
        List<String> expected = Arrays.asList(
            "(11, 2) -> (7, 10)",
            "(7, 10) -> (11, 2)",
            "(14, 7) -> (2, 1)",
            "(2, 1) -> (14, 7)");
        assertTrue(expected.contains(segments[0].toString()));
        assertTrue(expected.contains(segments[1].toString()));
    }

    @Test
    public void foundZeroSegments() {
        // given:
        Point[] points = initPointsWith0Segments();
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        // when:
        LineSegment[] segments = bcp.segments();

        // then:
        assertEquals(0, segments.length);
        assertEquals(0, bcp.numberOfSegments());
    }

}
