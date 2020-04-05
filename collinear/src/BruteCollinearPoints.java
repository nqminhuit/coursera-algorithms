import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private int numberOfSegments;

    private final Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Must not contain null array!");
        }

        int length = points.length;
        for (int i = 0; i < length; ++i) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Must not contain null Points!");
            }
        }

        for (int i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (0 == points[i].compareTo(points[j])) {
                    throw new IllegalArgumentException("Must not contain duplicate Points!");
                }
            }
        }

        numberOfSegments = 0;
        this.points = copyArray(points);
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    private Point[] copyArray(Point[] arrayToCopy) {
        Point[] copy = new Point[arrayToCopy.length];
        for (int i = 0; i < copy.length; ++i) {
            copy[i] = arrayToCopy[i];
        }
        return copy;
    }

    // the line segments
    public LineSegment[] segments() {
        int n = points.length;
        LineSegment[] ls = new LineSegment[n];
        int lsIndex = 0;
        Point[] tmpPoints = copyArray(points);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        double slopeJ = tmpPoints[i].slopeTo(tmpPoints[j]);
                        double slopeK = tmpPoints[i].slopeTo(tmpPoints[k]);
                        double slopeM = tmpPoints[i].slopeTo(tmpPoints[m]);
                        if (Double.compare(slopeJ, slopeK) == 0 && Double.compare(slopeK, slopeM) == 0) {
                            Point[] pointsInLine = new Point[4];
                            pointsInLine[0] = tmpPoints[i];
                            pointsInLine[1] = tmpPoints[j];
                            pointsInLine[2] = tmpPoints[k];
                            pointsInLine[3] = tmpPoints[m];

                            int min = min(pointsInLine);
                            int max = max(pointsInLine);

                            ls[lsIndex++] = new LineSegment(pointsInLine[min], pointsInLine[max]);
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
        LineSegment[] result = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; ++i) {
            result[i] = ls[i];
        }
        return result;
    }

    private int min(Point[] pointArray) {
        int min = 0;
        for (int i = 0; i < pointArray.length; ++i) {
            if (pointArray[i].compareTo(pointArray[min]) < 0) {
                min = i;
            }
        }
        return min;
    }

    private int max(Point[] pointArray) {
        int max = 0;
        for (int i = 0; i < pointArray.length; ++i) {
            if (pointArray[i].compareTo(pointArray[max]) > 0) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 8;
        Point[] points = new Point[n];
        points[0] = new Point(10_000, 0);
        points[1] = new Point(0, 10_000);
        points[2] = new Point(3_000, 7_000);
        points[3] = new Point(7_000, 3_000);
        points[4] = new Point(20_000, 21_000);
        points[5] = new Point(3_000, 4_000);
        points[6] = new Point(14_000, 15_000);
        points[7] = new Point(6_000, 7_000);

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
    }

}
