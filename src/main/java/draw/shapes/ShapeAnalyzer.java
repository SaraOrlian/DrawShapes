package draw.shapes;

import java.util.ArrayList;
import java.util.List;

import static draw.shapes.Shape.*;

/**
 * TODO: write javadoc
 */
public class ShapeAnalyzer {


    private List<Point> stroke;
    private final ShapeReducer REDUCER = new ShapeReducer();
    private final int VEE_CARAT_MIN_HEIGHT = 10;
    private final int VEE_CARAT_SKEW_ALLOWANCE = 500;
    private final int VEE_CARAT_EXPECTED_POINTS = 3;


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

        List<Point> smoothStroke = reduceCaratOrVee(stroke);
        return hasCorrectNumPointsForVeeOrCarat(smoothStroke)
                //&& isCentered(smoothStroke)
                && isVeeOrientation(smoothStroke);
    }

    public boolean isCarat(List<Point> stroke) {
        List<Point> smoothStroke = reduceCaratOrVee(stroke);
        return hasCorrectNumPointsForVeeOrCarat(smoothStroke)
                //&& isCentered(smoothStroke)
                && isCaratOrientation(smoothStroke);
    }

    private boolean hasOnlyValidSlopes(List<Point> stroke, Point start) {
        double prevSlope = 0;

        for (int i = 1; i < stroke.size(); i++) {
            double currSlope = calcSlope(stroke.get(i), stroke.get(i - 1));
            double horizontalErrorAllowance = 3;
            if (currSlope - prevSlope > horizontalErrorAllowance) {
                return false;
            }
            prevSlope = currSlope;
        }
        return true;
    }


    private boolean hasCorrectNumPointsForVeeOrCarat(List<Point> smoothStroke) {
        return smoothStroke.size() == VEE_CARAT_EXPECTED_POINTS;
    }

    private List<Point> reduceCaratOrVee(List<Point> stroke) {
        List<Point> smoothStroke = REDUCER.smooth(stroke);

        while (!hasCorrectNumPointsForVeeOrCarat(smoothStroke)) {
            int prevSize = smoothStroke.size();
            smoothStroke = REDUCER.smooth(smoothStroke);
            if (smoothStroke.size() == prevSize) {
                break;
            }
        }

        for (int i = 1; i < smoothStroke.size() - 3; i++) {
            if (smoothStroke.get(i).getDistance(smoothStroke.get(i + 1)) < 4) {
                smoothStroke.remove(i);
            }
        }
        return smoothStroke;
    }

//    private boolean isCentered(List<Point> smoothStroke) {
//        return notSkewedRight(smoothStroke)
//                && notSkewedLeft(smoothStroke);
//    }
//
//    private boolean notSkewedLeft(List<Point> smoothStroke) {
//        return smoothStroke.get(1).getX() < ((smoothStroke.get(0).getX() + smoothStroke.get(2).getX()) / 2) + VEE_CARAT_SKEW_ALLOWANCE;
//    }
//
//    private boolean notSkewedRight(List<Point> smoothStroke) {
//        return smoothStroke.get(1).getX() > ((smoothStroke.get(0).getX() + smoothStroke.get(2).getX()) / 2) - VEE_CARAT_SKEW_ALLOWANCE;
//    }

    private boolean isVeeOrientation(List<Point> smoothStroke) {
        return smoothStroke.get(1).getY() > smoothStroke.get(0).getY()
                && smoothStroke.get(1).getY() > smoothStroke.get(2).getY();
    }

    private boolean isCaratOrientation(List<Point> smoothStroke) {
        return smoothStroke.get(1).getY() < smoothStroke.get(0).getY()
                && smoothStroke.get(1).getY() < smoothStroke.get(2).getY();
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
