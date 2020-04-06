import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test
    public void foundTwoSegments() {
        // given:
        Point[] points = TestDataHelper.initPointsWith2Segments();
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        // when:
        LineSegment[] segments = bcp.segments();

        // then:
        assertEquals(2, bcp.numberOfSegments());
        List<String> expected = Arrays.asList(
            "(11, 2) -> (7, 10)",
            "(2, 1) -> (14, 7)");
        assertTrue(expected.contains(segments[0].toString()));
        assertTrue(expected.contains(segments[1].toString()));
    }

    @Test
    public void foundZeroSegments() {
        // given:
        Point[] points = TestDataHelper.initPointsWith0Segments();
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        // when:
        LineSegment[] segments = bcp.segments();

        // then:
        assertEquals(0, segments.length);
        assertEquals(0, bcp.numberOfSegments());
    }

}
