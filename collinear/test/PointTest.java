import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

public class PointTest {

    private static final double EPSILON = 0.000001D;

    @Test
    public void slopeTo() {
        // given:
        Point point = new Point(6, 1);
        Point secondPoint = new Point(4, 2);

        // when:
        double slope = point.slopeTo(secondPoint);

        // then:
        assertEquals(-0.5D, slope, EPSILON);
    }

    @Test
    public void compareY() {
        // given:
        Point point = new Point(6, 1);
        Point secondPoint = new Point(4, 2);

        // when:
        int compare = point.compareTo(secondPoint);

        // then:
        assertEquals(-1, compare);
    }

    @Test
    public void compareX() {
        // given:
        Point point = new Point(6, 2);
        Point secondPoint = new Point(4, 2);

        // when:
        int compare = point.compareTo(secondPoint);

        // then:
        assertEquals(2, compare);
    }

    @Test
    public void pointsEqual() {
        // given:
        Point point = new Point(6, 2);
        Point secondPoint = new Point(6, 2);

        // when:
        int compare = point.compareTo(secondPoint);

        // then:
        assertEquals(0, compare);
    }

    @Test
    public void sortPoints() {
        // given:
        int n = 5;
        Point[] points = new Point[n];
        points[0] = new Point(1, 4);
        points[1] = new Point(2, 3);
        points[2] = new Point(4, 2);
        points[3] = new Point(6, 1);
        points[4] = new Point(5, 1);

        // when:
        Arrays.sort(points);

        // then:
        Point[] expectedSortedPoints = new Point[n];
        expectedSortedPoints[0] = new Point(5, 1);
        expectedSortedPoints[1] = new Point(6, 1);
        expectedSortedPoints[2] = new Point(4, 2);
        expectedSortedPoints[3] = new Point(2, 3);
        expectedSortedPoints[4] = new Point(1, 4);

        for (int i = 0; i < n; ++i) {
            assertEquals(0, expectedSortedPoints[i].compareTo(points[i]));
        }
    }

    @Test
    public void pointComparator() {
        // given:
        Point p = new Point(3, 4);

        // when:
        Comparator<Point> slopeOrder = p.slopeOrder();

        // then:
        assertNotNull(slopeOrder);
    }

}
