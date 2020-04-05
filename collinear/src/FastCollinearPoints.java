import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private int numSegments;

    private final Point[] points;

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
        this.points = copyArray(newPoints);
    }

    // the number of line segments
    public int numberOfSegments() {
        return numSegments;
    }

    private Point[] copyArray(Point[] arrayToCopy) {
        Point[] copy = new Point[arrayToCopy.length];
        for (int i = 0; i < copy.length; ++i) {
            copy[i] = arrayToCopy[i];
        }
        return copy;
    }

    private Point[] getPointsInSameLine(Point[] pointToPartition, Point valuePoint, double slope) {
        Point[] tmpPoints = copyArray(pointToPartition);

        Arrays.sort(tmpPoints, valuePoint.slopeOrder());

        Point[] pointsInLine = new Point[tmpPoints.length];
        int pointIndex = 0;
        for (int i = 0; i < tmpPoints.length; ++i) {
            if (Double.compare(slope, valuePoint.slopeTo(tmpPoints[i])) == 0) {
                pointsInLine[pointIndex++] = tmpPoints[i];
            }
        }

        return truncateArray(pointsInLine, pointIndex);
    }

    private Point[] truncateArray(Point[] arrays, int length) {
        Point[] truncated = new Point[length];
        for (int i = 0; i < length; ++i) {
            truncated[i] = arrays[i];
        }
        return truncated;
    }

    // the line segments
    public LineSegment[] segments() {
        int length = points.length;
        LineSegment[] segments = new LineSegment[length - 1];
        for (int i = 0; i < length; ++i) {
            List<Double> checkInSlope = new ArrayList<>();
            for (int j = 0; j < length; ++j) {
                if (j == i) {
                    continue;
                }

                double slopeI = points[i].slopeTo(points[j]);
                if (checkInSlope.contains(slopeI)) {
                    continue;
                }

                Point[] pointsSameLine = getPointsInSameLine(points, points[i], slopeI);
                int pLength = pointsSameLine.length;
                if (pLength >= 3) {
                    Arrays.sort(pointsSameLine);
                    if (points[i].compareTo(pointsSameLine[0]) < 0) {
                        segments[numSegments++] = new LineSegment(points[i], pointsSameLine[pLength - 1]);
                    }
                    checkInSlope.add(slopeI);
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
