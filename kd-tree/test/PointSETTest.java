import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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

    @Test
    public void pointsInRange() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.000000D, 0.500000),
                new Point2D(0.500000D, 1.000000),
                new Point2D(0.500000D, 0.000000),
                new Point2D(1.000000D, 0.500000) };

        PointSET points = new PointSET();
        for (Point2D p : pointArray) {
            points.insert(p);
        }

        RectHV rect = new RectHV(0.25D, 0.25D, 1.5D, 1.5D);

        // when:
        Iterator<Point2D> iterator = points.range(rect).iterator();
        assertEquals(new Point2D(1.0D, 0.5D), iterator.next());
        assertEquals(new Point2D(0.5D, 1.0D), iterator.next());
    }

}
