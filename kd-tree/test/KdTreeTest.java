import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
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
    public void insertPoint_CheckForContaining() {
        // given:
        KdTree points = new KdTree();
        Point2D[] newPoints = {
                new Point2D(0.7D, 0.2D),
                new Point2D(0.5D, 0.4D),
                new Point2D(0.2D, 0.3D),
                new Point2D(0.4D, 0.7D),
                new Point2D(0.9D, 0.6D) };

        // when:
        Arrays.stream(newPoints).forEach(newPoint -> points.insert(newPoint));

        // then:
        assertEquals(5, points.size());
        assertTrue(points.contains(new Point2D(0.2D, 0.3D)));
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
    public void pointsSize_UpdateValueShouldRemainSameSize() {
        // given:
        KdTree points = new KdTree();

        // when:
        points.insert(new Point2D(1, 1));
        points.insert(new Point2D(2, 2));
        points.insert(new Point2D(2, 2));

        // then:
        assertEquals(2, points.size());
    }

    @Test
    public void pointsInRange() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.0D, 0.50D),
                new Point2D(0.5D, 1.0D),
                new Point2D(0.5D, 0.0D),
                new Point2D(1.0D, 0.5D) };

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
    public void pointsInRange_ShouldNotMiss() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.25D, 0.0D),
                new Point2D(1.0D, 0.25D),
                new Point2D(0.75D, 0.0D),
                new Point2D(0.75D, 1.0D),
                new Point2D(0.0D, 0.0D),
                new Point2D(0.0D, 0.5D),
                new Point2D(1.0D, 0.0D),
                new Point2D(0.75D, 0.5D),
                new Point2D(0.5D, 0.25D),
                new Point2D(0.5D, 1.0D) };

        KdTree points = new KdTree();
        for (Point2D p : pointArray) {
            points.insert(p);
        }
        assertEquals(10, points.size());

        RectHV rect = new RectHV(0.25D, 0.75D, 1.0D, 1.0D);

        // when:
        Iterator<Point2D> iterator = points.range(rect).iterator();

        // then:
        assertEquals(new Point2D(0.75D, 1.0D), iterator.next());
        assertEquals(new Point2D(0.5D, 1.0D), iterator.next());
    }

    @Test
    public void nearestPoint() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.0D, 0.5D),
                new Point2D(0.5D, 1.0D),
                new Point2D(0.5D, 0.0D),
                new Point2D(1.0D, 0.5D) };

        KdTree points = new KdTree();
        for (Point2D p : pointArray) {
            points.insert(p);
        }

        // when:
        Point2D nearestPoint = points.nearest(new Point2D(0.25D, 1.5D));

        // then:
        assertEquals(new Point2D(0.5D, 1.0D), nearestPoint);
    }

    @Test
    public void nearestPoint_ShoudReturnCorrectWhenContiniouslyQuerying() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.7D, 0.2D),
                new Point2D(0.5D, 0.4D),
                new Point2D(0.2D, 0.3D),
                new Point2D(0.4D, 0.7D),
                new Point2D(0.9D, 0.6D) };

        KdTree points = new KdTree();
        Arrays.stream(pointArray).forEach(point -> points.insert(point));
        assertEquals(5, points.size());

        // when:
        assertEquals(new Point2D(0.2D, 0.3D), points.nearest(new Point2D(0.371D, 0.034D)));
        assertEquals(new Point2D(0.2D, 0.3D), points.nearest(new Point2D(0.013D, 0.46D)));
        assertEquals(new Point2D(0.7D, 0.2D), points.nearest(new Point2D(0.937D, 0.3D)));
        assertEquals(new Point2D(0.4D, 0.7D), points.nearest(new Point2D(0.57D, 0.784D)));
        assertEquals(new Point2D(0.4D, 0.7D), points.nearest(new Point2D(0.705D, 0.989D)));
    }

    @Test
    public void contains_OK() {
        // given:
        KdTree points = new KdTree();
        points.insert(new Point2D(1.0D, 1.0D));
        points.insert(new Point2D(1.0D, 0.0D));

        // when:
        boolean contains = points.contains(new Point2D(1.0D, 0.0D));

        // then:
        assertEquals(2, points.size());
        assertTrue(contains);
    }

    @Test
    public void contains_NOK() {
        // given:
        KdTree points = new KdTree();
        points.insert(new Point2D(1.0D, 1.0D));

        // when:
        boolean contains = points.contains(new Point2D(1.0D, 0.0D));

        // then:
        assertFalse(contains);
    }

    @Test
    public void nearestPoint_() {
        // given:
        Point2D[] pointArray = {
                new Point2D(0.7D, 0.2D),
                new Point2D(0.5D, 0.4D),
                new Point2D(0.2D, 0.3D),
                new Point2D(0.4D, 0.7D),
                new Point2D(0.9D, 0.6D) };

        KdTree points = new KdTree();
        Arrays.stream(pointArray).forEach(point -> points.insert(point));
        assertEquals(5, points.size());

        // when:
        assertEquals(new Point2D(0.2D, 0.3D), points.nearest(new Point2D(0.13D, 0.46D)));
    }

}
