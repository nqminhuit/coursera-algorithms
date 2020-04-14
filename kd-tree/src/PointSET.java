import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

    private Set<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }

        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) {
            throw new IllegalArgumentException();
        }
        List<Point2D> pointsInRange = new ArrayList<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                pointsInRange.add(point);
            }
        }
        return pointsInRange;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }

        if (points.isEmpty()) {
            return null;
        }

        double nearestDistance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;
        for (Point2D point : points) {
            double distanceToP = point.distanceTo(p);
            if (distanceToP < nearestDistance) {
                nearestDistance = distanceToP;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

}
