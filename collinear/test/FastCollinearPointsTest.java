import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FastCollinearPointsTest {

    @Test
    public void partitionParallel() {
        // given:
        Point[] points = TestDataHelper.initPointsWithParallelSegments();
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // when:
        LineSegment[] segments = fcp.segments();

        // then:
        assertEquals(0, segments.length);
    }

    @Test
    public void foundTwoSegments() {
        // given:
        Point[] points = TestDataHelper.initPointsWith2Segments();
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // when:
        LineSegment[] segments = fcp.segments();

        // then:
        assertEquals(2, fcp.numberOfSegments());
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
        Point[] points = TestDataHelper.initPointsWith0Segments();
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // when:
        LineSegment[] segments = fcp.segments();

        // then:
        assertEquals(0, segments.length);
        assertEquals(0, fcp.numberOfSegments());
    }

    @Test
    public void partition3PointsInSegments() {
        // given:
        Point[] points = TestDataHelper.initPointsWith3PointInSegment();
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // when:
        LineSegment[] segments = fcp.segments();

        // then:
        assertEquals(0, segments.length);
    }

    @Test
    public void found4Segments() {
        // given:
        Point[] points = TestDataHelper.initWith4Segments();
        FastCollinearPoints fcp = new FastCollinearPoints(points);

        // when:
        fcp.segments();

        // then:
        assertEquals(4, fcp.numberOfSegments());
    }

    // test with 1 point belongs to both segments

}
