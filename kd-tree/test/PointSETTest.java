import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import edu.princeton.cs.algs4.Point2D;

public class PointSETTest {

    @Test(expected = IllegalArgumentException.class)
    public void insertPoint_preventNull() {
        // given:
        PointSET points = new PointSET();

        // when:
        points.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsPoint_preventNull() {
        // given:
        PointSET points = new PointSET();

        // when:
        points.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void range_preventNull() {
        // given:
        PointSET points = new PointSET();

        // when:
        points.range(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nearest_preventNull() {
        // given:
        PointSET points = new PointSET();

        // when:
        points.nearest(null);
    }

    @Test
    public void insertPoint_Left() {
        // given:
        PointSET points = new PointSET();
        Point2D newPoint = new Point2D(1, 1);

        // when:
        points.insert(newPoint);

        // then:
        assertEquals(1, points.size());
        assertTrue(points.contains(newPoint));
    }

    @Test
    public void emptyPoints() {
        // given:
        PointSET points = new PointSET();

        // when:
        boolean isEmpty = points.isEmpty();

        // then:
        assertTrue(isEmpty);
    }

    @Test
    public void notEmptyPoints() {
        // given:
        PointSET points = new PointSET();
        Point2D newPoint = new Point2D(1, 1);
        points.insert(newPoint);

        // then:
        assertFalse(points.isEmpty());
    }

    @Test
    public void pointsSize() {
        // given:
        PointSET points = new PointSET();

        // when:
        points.insert(new Point2D(1, 1));
        points.insert(new Point2D(2, 2));

        // then:
        assertEquals(2, points.size());
    }

}
