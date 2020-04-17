import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void insertPoint_preventNull() {
        // given:
        KdTree points = new KdTree();

        // when:
        points.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsPoint_preventNull() {
        // given:
        KdTree points = new KdTree();

        // when:
        points.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void range_preventNull() {
        // given:
        KdTree points = new KdTree();

        // when:
        points.range(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nearest_preventNull() {
        // given:
        KdTree points = new KdTree();

        // when:
        points.nearest(null);
    }

    @Test
    public void insertPoint() {
        // given:
        KdTree points = new KdTree();
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
        KdTree points = new KdTree();

        // when:
        boolean isEmpty = points.isEmpty();

        // then:
        assertTrue(isEmpty);
    }

    @Test
    public void notEmptyPoints() {
        // given:
        KdTree points = new KdTree();
        Point2D newPoint = new Point2D(1, 1);
        points.insert(newPoint);

        // then:
        assertFalse(points.isEmpty());
    }

    @Test
    public void pointsSize() {
        // given:
        KdTree points = new KdTree();

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

        KdTree points = new KdTree();
        for (Point2D p : pointArray) {
            points.insert(p);
        }

        RectHV rect = new RectHV(0.25D, 0.25D, 1.5D, 1.5D);

        // when:
        Iterator<Point2D> iterator = points.range(rect).iterator();
        assertEquals(new Point2D(0.5D, 1.0D), iterator.next());
        assertEquals(new Point2D(1.0D, 0.5D), iterator.next());
    }

    @Test
    public void nearestPoint() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.000000D, 0.500000),
                new Point2D(0.500000D, 1.000000),
                new Point2D(0.500000D, 0.000000),
                new Point2D(1.000000D, 0.500000) };

        KdTree points = new KdTree();
        for (Point2D p : pointArray) {
            points.insert(p);
        }

        // when:
        Point2D nearestPoint = points.nearest(new Point2D(0.25D, 1.5D));

        // then:
        assertEquals(new Point2D(0.5D, 1.0D), nearestPoint);
    }

}
