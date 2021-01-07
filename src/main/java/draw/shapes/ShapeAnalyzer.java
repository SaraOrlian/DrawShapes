package draw.shapes;

import java.util.ArrayList;
import java.util.List;

import static draw.shapes.Shape.*;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {


    List<Point> stroke;

    ShapeReducer reducer = new ShapeReducer();


    public Shape getShape(List<Point> stroke) {
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
        final int vertices = 3;
        int errorAllowance = 50;
        List<Point> smoothStroke = new ArrayList<Point>();

        while (smoothStroke.size() != 3) {
            smoothStroke = reducer.smooth(smoothStroke);
        }
        return smoothStroke.get(1).getX() > ((smoothStroke.get(0).getX() + smoothStroke.get(2).getX()) / 2) - errorAllowance
                && smoothStroke.get(1).getX() < ((smoothStroke.get(0).getX() + smoothStroke.get(2).getX()) / 2) + errorAllowance
                && smoothStroke.get(1).getY() > ((smoothStroke.get(0).getY() + smoothStroke.get(2).getY()) / 2) + errorAllowance;


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
