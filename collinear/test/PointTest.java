import static org.junit.Assert.assertEquals;
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
}
