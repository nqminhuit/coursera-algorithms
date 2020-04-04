import java.util.Arrays;

public class FastCollinearPoints {

    private int numSegments;

    private final Point[] points;

    private double[] calculatedSlope;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] newPoints) {
        if (newPoints == null) {
            throw new IllegalArgumentException("Must not contain null array!");
        }

        int pointLength = newPoints.length;
        for (int i = 0; i < pointLength; ++i) {
            if (newPoints[i] == null) {
                throw new IllegalArgumentException("Must not contain null Points!");
            }
        }

        for (int i = 0; i < pointLength; ++i) {
            for (int j = i + 1; j < pointLength; ++j) {
                if (0 == newPoints[i].compareTo(newPoints[j])) {
                    throw new IllegalArgumentException("Must not contain duplicate Points!");
                }
            }
        }

        numSegments = 0;
        this.points = new Point[pointLength];
        for (int i = 0; i < pointLength; ++i) {
            this.points[i] = newPoints[i];
        }
        this.calculatedSlope = new double[pointLength - 1];
    }

    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }

    private boolean isSlopeCalculated(double slope) {
        boolean isSlopeCalculated = false;
        for (int j = 0; j < numSegments; ++j) {
            if (slope == calculatedSlope[j]) {
                isSlopeCalculated = true;
                break;
            }
        }
        return isSlopeCalculated;
    }

    private Point[] partition(Point[] points, Point valuePoint, double slope) {
        int i = 0;
        int gt = points.length - 1;
        int lt = 0;

        while (i <= gt) {
            if (valuePoint.slopeTo(points[i]) == Double.NEGATIVE_INFINITY) {
                ++i;
            } else if (valuePoint.slopeTo(points[i]) < slope) {
                exchangePoints(points, i++, lt++);
            } else if (valuePoint.slopeTo(points[i]) > slope) {
                exchangePoints(points, i, gt--);
            } else {
                ++i;
            }
        }

        Point[] pointsInLine = new Point[gt - lt + 1];
        for (int j = lt; j <= gt; ++j) {
            pointsInLine[j - lt] = points[j];
        }

        return pointsInLine;
    }

    private void exchangePoints(Point[] pointArray, int i, int j) {
        Point temp = pointArray[i];
        pointArray[i] = pointArray[j];
        pointArray[j] = temp;
    }

    // the line segments
    public LineSegment[] segments() {
        int length = points.length;
        LineSegment[] segments = new LineSegment[length - 1];
        for (int i = 0; i < length - 1; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i == j) {
                    continue;
                }
                double slopeI = points[i].slopeTo(points[j]);
                if (isSlopeCalculated(slopeI)) {
                    continue;
                }

                // Arrays.sort(points, points[i].slopeOrder());
                Point[] partitionedPoints = partition(points, points[i], slopeI);
                int pLength = partitionedPoints.length;
                if (pLength > 3) {
                    Arrays.sort(partitionedPoints);
                    calculatedSlope[numSegments] = slopeI;
                    segments[numSegments] =
                        new LineSegment(partitionedPoints[0], partitionedPoints[pLength - 1]);
                    numSegments++;
                }
            }
        }

        return truncateLineSegment(segments);
    }

    private LineSegment[] truncateLineSegment(LineSegment[] ls) {
        LineSegment[] copy = new LineSegment[numSegments];
        for (int i = 0; i < numSegments; ++i) {
            copy[i] = ls[i];
        }
        return copy;
    }
}
