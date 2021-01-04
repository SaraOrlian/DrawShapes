package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {

    private final double HORIZONTAL_ERROR_ALLOWANCE = 3;
    private final double V_ERROR_ALLOWANCE = 10;
    private final double MIN_POS_SLOPE = 0.47;
    private final double MAX_NEG_SLOPE = -0.42;

    public boolean isHorizontal(List<Point> stroke) {
        Point start = stroke.get(0);
        Point end = stroke.get(stroke.size() - 1);
        double slope = calcSlope(start, end);

        if (!isInZeroRange(slope)) {
            return false;
        } else {
            return hasOnlyValidSlopes(stroke, start);
        }
    }

    private boolean hasOnlyValidSlopes(List<Point> stroke, Point start) {
        Point previousPoint = start;
        double prevSlope = 0;
        for (Point point : stroke) {
            double currSlope = calcSlope(point, previousPoint);
            if (currSlope - prevSlope > HORIZONTAL_ERROR_ALLOWANCE) {
                return false;
            }
            prevSlope = currSlope;
            previousPoint = point;
        }
        return true;
    }

    public boolean isVertical(List<Point> stroke) {
        for (Point point : stroke) {
            point.invert();
        }
        return isHorizontal(stroke);
    }

    public boolean isV(List<Point> stroke) {

        Point start = stroke.get(0);
        Point end = stroke.get(stroke.size() - 1);
        if (Math.abs(start.getY()) - end.getY() > V_ERROR_ALLOWANCE) {
            return false;
        }
        Point vertex = null;
        Point previousPoint = start;
        int vertexIndex = 0;
        double prevSlope = 0;
        for (int i = 0; i < stroke.size(); i++) {
            double currSlope = calcSlope(stroke.get(i), previousPoint);
            if (currSlope > 0) {
                vertex = previousPoint;
                vertexIndex = i - 1;
                break;
            }
            if (currSlope - prevSlope > V_ERROR_ALLOWANCE) {
                return false;
            }
            prevSlope = currSlope;
            previousPoint = stroke.get(i);
        }
        if (vertex == null) {
            return false;
        }
//        for (int i = vertexIndex + (int) V_ERROR_ALLOWANCE; i < stroke.size(); i++) {
//            double currSlope = calcSlope(stroke.get(i), previousPoint);
//            if (currSlope < 0) {
//                return false;
//
//            }
//            if (currSlope - prevSlope > V_ERROR_ALLOWANCE) {
//                return false;
//            }
//            prevSlope = currSlope;
//            previousPoint = stroke.get(i);
//        }

        return (calcSlope(start, vertex) < 0 && calcSlope(vertex, end) > 0);
    }

    public boolean isCarat(List<Point> stroke) {
        Point start = stroke.get(0);
        Point end = stroke.get(stroke.size() - 1);
        if (Math.abs(start.getY()) - end.getY() > V_ERROR_ALLOWANCE) {
            return false;
        }
        Point vertex = null;
        Point previousPoint = start;
        double prevSlope = 0;
        for (Point point : stroke) {
            double currSlope = calcSlope(point, previousPoint);
            if (currSlope < 0) {
                vertex = previousPoint;
                break;
            }
            if (currSlope - prevSlope > V_ERROR_ALLOWANCE) {
                return false;
            }
            prevSlope = currSlope;
            previousPoint = point;
        }
        if (vertex == null) {
            return false;
        }

        return true;
    }


    private boolean isInZeroRange(double slope) {
        return slope > MAX_NEG_SLOPE && slope < MIN_POS_SLOPE;
    }

    private double calcSlope(Point point1, Point point2) {
        return -1 * (point1.getY() - point2.getY()) / (double) (point1.getX() - point2.getX());
    }

}
