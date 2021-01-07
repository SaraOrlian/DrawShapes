package draw.shapes;

import java.util.ArrayList;
import java.util.List;

import static draw.shapes.Shape.*;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {

    List<Point> stroke;

    private final double ANGLE_RANGE_ALLOWANCE = Math.PI / 9;


    public void setStroke(List<Point> stroke) {
        this.stroke = new ArrayList<>(stroke);
    }

    public Shape strokeIsShape() {
        if (isHorizontal(stroke)) {
            return HORIZONTAL;
        }
        if (isVertical(stroke)) {
            return VERTICAL;
        }
        if (isCarat(stroke)) {
            return CARAT;
        }
        if (isVee(stroke)) {
            return VEE;
        }
        return null;
    }

    public boolean isHorizontal(List<Point> stroke) {
        Point start = stroke.get(0);
        Point end = stroke.get(stroke.size() - 1);
        double slope = calcSlope(start, end);

        return isInZeroRange(slope) && hasOnlyValidSlopes(stroke, start);

    }

    private boolean hasOnlyValidSlopes(List<Point> stroke, Point start) {
        Point previousPoint = start;
        double prevSlope = 0;
        //take out starts and dont use foreach
        for (Point point : stroke) {
            double currSlope = calcSlope(point, previousPoint);
            double HORIZONTAL_ERROR_ALLOWANCE = 3;
            if (currSlope - prevSlope > HORIZONTAL_ERROR_ALLOWANCE) {
                return false;
            }
            prevSlope = currSlope;
            previousPoint = point;
        }
        return true;
    }

    /**
     * explain inversion
     *
     * @param stroke
     * @return
     */

    public boolean isVertical(List<Point> stroke) {
        List<Point> newStroke = new ArrayList<>();
        for (Point point : stroke) {

            newStroke.add(point.invert());
        }
        return isHorizontal(newStroke);
    }

    public boolean isVee(List<Point> stroke) {
//stach exchange link, unit crcle link in javadoc
        double[] leftRightVector1 = {1, 1.5};
        double[] leftRightVector2 = {1, -1.5};
        double[] rightLeftVector1 = {-1, 1.5};
        double[] rightLeftVector2 = {-1, -1.5};
        double scale = 1;
        final double EXPECTED_ANGLE = Math.PI / 6;
        final double JITTER_RANGE = Math.PI / 12;
        int lineStart = 0;
//add distance method to point
        for (int i = 0; i < stroke.size(); i++) {
            if (passesThresholdAngle(stroke, EXPECTED_ANGLE, JITTER_RANGE, lineStart, i)) {
                scale = getDistance(stroke.get(lineStart), stroke.get(i));
                leftRightVector1[0] += stroke.get(lineStart).getX();
                leftRightVector1[1] += stroke.get(lineStart).getY();
                leftRightVector2[0] += stroke.get(i).getX();
                leftRightVector2[1] += stroke.get(i).getX();
                lineStart = i;
            }
        }
        return  false;
    }

    private double getDistance(Point point1, Point point2) {
        double xDiff = point2.getX() - point1.getX();
        double yDiff = point2.getY() - point1.getY();

        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    private boolean passesThresholdAngle(List<Point> stroke, double EXPECTED_ANGLE, double JITTER_RANGE, int lineStart, int i) {
        return isGreaterThanThresholdRange(stroke, EXPECTED_ANGLE, JITTER_RANGE, lineStart, i)
                || isLessThanThresholdRange(stroke, EXPECTED_ANGLE, JITTER_RANGE, lineStart, i);
    }

    private boolean isLessThanThresholdRange(List<Point> stroke, double EXPECTED_ANGLE, double JITTER_RANGE, int lineStart, int i) {
        return getRadiansFromOrigin(stroke.get(i), stroke.get(lineStart)) < EXPECTED_ANGLE - JITTER_RANGE;
    }


    private boolean isGreaterThanThresholdRange(List<Point> stroke, double EXPECTED_ANGLE, double JITTER_RANGE, int lineStart, int i) {
        return getRadiansFromOrigin(stroke.get(i), stroke.get(lineStart)) > EXPECTED_ANGLE + JITTER_RANGE;
    }


    private double getRadiansFromOrigin(Point pt, Point origin) {

        double opposite = pt.getY() - origin.getY();
        double adjacent = pt.getX() - origin.getX();
        return Math.atan(opposite / adjacent);
    }


    public boolean isCarat(List<Point> stroke) {
        return false;
    }

    /**
     * checks if slope is within the loose allowance of a zero slope
     *
     * @param slope
     * @return
     */
    private boolean isInZeroRange(double slope) {
        double MAX_NEG_SLOPE = -0.42;
        double MIN_POS_SLOPE = 0.47;
        return slope > MAX_NEG_SLOPE && slope < MIN_POS_SLOPE;
    }

    public double calcSlope(Point point1, Point point2) {
        try {
            return -1 * (point1.getY() - point2.getY()) / (double) (point1.getX() - point2.getX());
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    //testing
    public void whichStroke() {
        if (isHorizontal(stroke)) {
            System.out.println("horizontal");
        } else if (isVertical(stroke)) {
            System.out.println("vertical");
        } else if (isCarat(stroke)) {
            System.out.println("^");
        } else if (isVee(stroke)) {
            System.out.println("v");
        } else {
            System.out.println("nothing");
        }
    }

}
