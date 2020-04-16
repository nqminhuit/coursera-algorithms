import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final int X_MAX = 1;

    private static final int X_MIN = 0;

    private static final int Y_MAX = 1;

    private static final int Y_MIN = 0;

    private int size;

    private Node root;

    private RectHV queryRect;

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
        queryRect = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return this.size;
    }

    private double comparePointsWithLevel(Node node, Point2D p) {
        double cmp;
        if (node.level % 2 == 0) {
            cmp = node.point.x() - p.x();
        }
        else {
            cmp = node.point.y() - p.y();
        }
        return cmp;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }
        root = put(root, p, 0);
        size++;
    }

    private Node put(Node node, Point2D p, int level) {
        if (node == null) {
            return new Node(p, level);
        }

        double cmp = comparePointsWithLevel(node, p);

        if (cmp > 0) {
            node.left_below = put(node.left_below, p, node.level + 1);
        }
        else if (cmp < 0) {
            node.right_above = put(node.right_above, p, node.level + 1);
        }
        else {
            node.point = p;
        }

        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }

        Node node = root;
        while (node != null) {
            double cmp = comparePointsWithLevel(node, p);
            if (cmp < 0) {
                node = node.left_below;
            }
            else if (cmp > 0) {
                node = node.right_above;
            }
            else {
                return true;
            }
        }

        return false;
    }

    // draw all points to standard draw
    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        Point2D point = node.point;
        StdDraw.point(point.x(), point.y());
        draw(node.left_below);
        draw(node.right_above);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (null == rect) {
            throw new IllegalArgumentException();
        }
        queryRect = rect;
        List<Point2D> pointsInRange = new ArrayList<>();
        pointInRange(root, pointsInRange, X_MIN, Y_MIN, X_MAX, Y_MAX);
        return pointsInRange;
    }

    private void pointInRange(Node node, List<Point2D> pointsInRange,
        double x_min, double y_min, double x_max, double y_max) {

        if (node == null) {
            return;
        }

        if (!queryRect.intersects(new RectHV(x_min, y_min, x_max, y_max))) {
            return;
        }

        Point2D point = node.point;
        if (queryRect.contains(point)) {
            pointsInRange.add(point);
        }

        if (node.level % 2 == 0) {
            pointInRange(node.left_below, pointsInRange, x_min, y_min, point.x(), y_max);
            pointInRange(node.right_above, pointsInRange, point.x(), y_min, x_max, y_max);
        }
        else {
            pointInRange(node.left_below, pointsInRange, x_min, y_min, x_max, point.y());
            pointInRange(node.right_above, pointsInRange, x_min, point.y(), x_max, y_max);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    private class Node {

        private int level;

        private Point2D point;

        private Node left_below, right_above;

        public Node(Point2D point, int level) {
            this.point = point;
            this.level = level;
        }

    }

}
