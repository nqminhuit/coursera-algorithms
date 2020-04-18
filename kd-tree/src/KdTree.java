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

    private Point2D nearestPoint;

    private double nearestDistance;

    private Point2D queryPoint;

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
        queryRect = null;
        queryPoint = null;
        nearestPoint = null;
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
        if (node.point.equals(p)) {
            return 0D;
        }
        if (node.level % 2 == 0) {
            return node.point.x() - p.x() == 0D ? -1 : node.point.x() - p.x();
        }
        return node.point.y() - p.y() == 0D ? -1 : node.point.y() - p.y();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }
        root = put(root, p, 0);
    }

    private Node put(Node node, Point2D p, int level) {
        if (node == null) {
            size++;
            return new Node(p, level);
        }

        double cmp = comparePointsWithLevel(node, p);

        if (cmp > 0) {
            node.leftBelow = put(node.leftBelow, p, node.level + 1);
        }
        else if (cmp < 0) {
            node.rightAbove = put(node.rightAbove, p, node.level + 1);
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
            if (cmp > 0) {
                node = node.leftBelow;
            }
            else if (cmp < 0) {
                node = node.rightAbove;
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
        draw(node.leftBelow);
        draw(node.rightAbove);
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
        double xMin, double yMin, double xMax, double yMax) {

        if (node == null) {
            return;
        }

        if (!queryRect.intersects(new RectHV(xMin, yMin, xMax, yMax))) {
            return;
        }

        Point2D point = node.point;
        if (queryRect.contains(point)) {
            pointsInRange.add(point);
        }

        if (node.level % 2 == 0) {
            double x = point.x();
            pointInRange(node.leftBelow, pointsInRange, xMin, yMin, x, yMax);
            pointInRange(node.rightAbove, pointsInRange, x, yMin, xMax, yMax);
        }
        else {
            double y = point.y();
            pointInRange(node.leftBelow, pointsInRange, xMin, yMin, xMax, y);
            pointInRange(node.rightAbove, pointsInRange, xMin, y, xMax, yMax);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (null == p) {
            throw new IllegalArgumentException();
        }

        this.nearestDistance = Double.POSITIVE_INFINITY;

        this.queryPoint = p;

        findNearestPoint(root, X_MIN, Y_MIN, X_MAX, Y_MAX);

        return this.nearestPoint;
    }

    private double min(double a, double b) {
        return a - b < 0 ? a : b;
    }

    private boolean equals(double a, double b) {
        return a - b == 0D ? true : false;
    }

    private void findNearestPoint(Node node, double xMin, double yMin, double xMax, double yMax) {
        if (node == null) {
            return;
        }

        Point2D point = node.point;
        double distance = point.distanceSquaredTo(queryPoint);
        if (distance <= nearestDistance) {
            nearestPoint = point;
            nearestDistance = distance;
        }

        if (node.level % 2 == 0) {
            double pointX = point.x();
            double dstLeftQuery = new RectHV(xMin, yMin, pointX, yMax).distanceSquaredTo(queryPoint);
            double dstRightQuery = new RectHV(pointX, yMin, xMax, yMax).distanceSquaredTo(queryPoint);
            double dstMinQuery = min(dstLeftQuery, dstRightQuery);

            if (equals(dstMinQuery, dstLeftQuery)) {
                if (dstLeftQuery <= nearestDistance) {
                    findNearestPoint(node.leftBelow, xMin, yMin, pointX, yMax);
                }
                if (dstRightQuery <= nearestDistance) {
                    findNearestPoint(node.rightAbove, pointX, yMin, xMax, yMax);
                }
            }
            else if (equals(dstMinQuery, dstRightQuery)) {
                if (dstRightQuery <= nearestDistance) {
                    findNearestPoint(node.rightAbove, pointX, yMin, xMax, yMax);
                }
                if (dstLeftQuery <= nearestDistance) {
                    findNearestPoint(node.leftBelow, xMin, yMin, pointX, yMax);
                }
            }
        }
        else {
            double pointY = point.y();
            double dstBelowQuery = new RectHV(xMin, yMin, xMax, pointY).distanceSquaredTo(queryPoint);
            double dstAboveQuery = new RectHV(xMin, pointY, xMax, yMax).distanceSquaredTo(queryPoint);
            double dstMinQuery = min(dstBelowQuery, dstAboveQuery);

            if (equals(dstMinQuery, dstBelowQuery)) {
                if (dstBelowQuery <= nearestDistance) {
                    findNearestPoint(node.leftBelow, xMin, yMin, xMax, pointY);
                }
                if (dstAboveQuery <= nearestDistance) {
                    findNearestPoint(node.rightAbove, xMin, pointY, xMax, yMax);
                }
            }
            else if (equals(dstMinQuery, dstAboveQuery)) {
                if (dstAboveQuery <= nearestDistance) {
                    findNearestPoint(node.rightAbove, xMin, pointY, xMax, yMax);
                }
                if (dstBelowQuery <= nearestDistance) {
                    findNearestPoint(node.leftBelow, xMin, yMin, xMax, pointY);
                }
            }
        }
    }

    private class Node {

        private final int level;

        private Point2D point;

        private Node leftBelow, rightAbove;

        public Node(Point2D point, int level) {
            this.point = point;
            this.level = level;
        }
    }
}
