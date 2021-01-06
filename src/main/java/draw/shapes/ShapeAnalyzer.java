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

        int vertexIndex = findVeeVertexIndex(stroke);
        int zeroY = stroke.get(vertexIndex).getX(); // define a relative x axis
        int zeroX = stroke.get(vertexIndex).getY(); // define a relative y axis
        Point origin = new Point(zeroX, zeroY); //define a relative origin


        return isDownLine(stroke, origin, vertexIndex) && isUpLine(stroke, origin, vertexIndex);
    }

    public boolean isDownLine(List<Point> stroke, Point origin, int vertexIndex) {
        double maxAngle = Double.MIN_VALUE;
        double minAngle = Double.MAX_VALUE;
        double MAX_POS_ANGLE = Math.PI / 3;
        double MIN_POS_ANGLE = Math.PI / 6;


        
        for (int i = vertexIndex; i < stroke.size(); i++) {
            double currentAngle = getRadiansFromXAxis(stroke.get(i), origin);

            if (currentAngle > MAX_POS_ANGLE || currentAngle < MIN_POS_ANGLE) {
                return false;
            }

            if (currentAngle > maxAngle) {
                maxAngle = currentAngle;
            }
            if (currentAngle < minAngle) {
                minAngle = currentAngle;
            }
        }
        return maxAngle - minAngle < ANGLE_RANGE_ALLOWANCE;
    }
    public boolean isUpLine(List<Point> stroke, Point origin, int vertexIndex) {

        double maxAngle = Double.MIN_VALUE;
        double minAngle = Double.MAX_VALUE;
        for (int i = vertexIndex; i < stroke.size(); i++) {
            double currentAngle = getRadiansFromXAxis(stroke.get(i), origin);

            double MAX_POS_ANGLE = Math.PI / 3;
            double MIN_POS_ANGLE = Math.PI / 6;
            if (currentAngle > MAX_POS_ANGLE || currentAngle < MIN_POS_ANGLE) {
                return false;
            }

            if (currentAngle > maxAngle) {
                maxAngle = currentAngle;
            }
            if (currentAngle < minAngle) {
                minAngle = currentAngle;
            }
        }
        return maxAngle - minAngle < ANGLE_RANGE_ALLOWANCE;
    }

    private double getRadiansFromXAxis(Point pt, Point origin) {
        Point point = pt;
        double opposite = Math.abs(point.getY() - origin.getY());
        double adjacent = Math.abs(point.getX() - origin.getX());
        double angle=  Math.atan(opposite / adjacent);
        return angle;
    }

    private int findVeeVertexIndex(List<Point> stroke) {
        int vertexIndex = 0;

        for (int i = 0; i < stroke.size(); i++) {
            if (stroke.get(i).getY() > stroke.get(vertexIndex).getY()) {
                vertexIndex = i;
            }
        }
        return vertexIndex;
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
        } else
        if (isVertical(stroke)) {
            System.out.println("vertical");
        } else
        if (isCarat(stroke)) {
            System.out.println("^");
        } else
        if (isVee(stroke)) {
            System.out.println("v");
        } else {
            System.out.println("nothing");
        }
    }

}
