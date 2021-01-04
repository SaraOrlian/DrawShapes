package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {

    private final double HORIZONTAL_ERROR_ALLOWANCE = 5;
    private final double V_ERROR_ALLOWANCE = 10;

    private final double MAX_POS_SLOPE = 3;
    private final double MIN_POS_SLOPE = 0.47;
    private final double MAX_NEG_SLOPE = -0.42;
    private final double MIN_NEG_SLOPE = -3;

    //smooth out line by reducing little lines onto one large line where slopes are all similar, so
    // that you only look at the points that define the shape
    //1- draw visually 2- reduce 3- test with perfect coordinates
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
        double prevSlope = 0;
        for (Point point : stroke) {
            double currSlope = calcSlope(point, previousPoint);
            if (currSlope > 0) {
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

    private boolean isInPosRange(double slope) {
        return slope > MIN_POS_SLOPE && slope < MAX_POS_SLOPE;
    }

    private boolean isInNegRange(double slope) {
        return slope > MIN_NEG_SLOPE && slope < MAX_NEG_SLOPE;
    }

    private boolean isInUndefRange(double slope) {
        return slope > MAX_POS_SLOPE || slope < MAX_NEG_SLOPE;
    }


    private double calcSlope(Point point1, Point point2) {
        return -1 * (point1.getY() - point2.getY()) / (double) (point1.getX() - point2.getX());
    }

}
