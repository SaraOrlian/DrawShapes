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
        double MAX_DOWN_ANGLE = 2*Math.PI / 3;
        double MIN_DOWN_ANGLE = 5*Math.PI / 6;
        double currentAngle = 0;

        for (int i = 0; i < vertexIndex; i++) {
            currentAngle = getRadiansFromXAxis(stroke.get(i), origin);
            if (currentAngle < MIN_DOWN_ANGLE || currentAngle > MAX_DOWN_ANGLE || stroke.get(i).getY() < origin.getY()) {
                return false;
            }
        }
        return true;
    }


    public boolean isUpLine(List<Point> stroke, Point origin, int startIndex) {

        double maxAngle = Double.MIN_VALUE;
        double minAngle = Double.MAX_VALUE;
        double MAX_UP_ANGLE = Math.PI / 3;
        double MIN_UP_ANGLE = Math.PI / 6;
        double currentAngle = 0;

        for (int i = startIndex; i < stroke.size(); i++) {
            currentAngle = getRadiansFromXAxis(stroke.get(i), origin);
            if (currentAngle < MIN_UP_ANGLE || currentAngle > MAX_UP_ANGLE || stroke.get(i).getY() > origin.getY()) {
                return false;
            }
        }
        return true;
    }

    private double getRadiansFromXAxis(Point pt, Point origin) {
        double opposite = 0;
        double adjacent = 0;
        if (pt.getY() > origin.getY()) {
            opposite = pt.getY() - origin.getY();
            adjacent = pt.getX() - origin.getX();

        } else {
            opposite = origin.getY() - pt.getY();
            adjacent = origin.getX() - pt.getX();
        }

        return Math.atan(opposite / adjacent);
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
