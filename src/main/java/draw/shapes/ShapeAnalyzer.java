package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {
    //private List<Point> stroke;
    private final int ERROR_ALLOWANCE = 5;

    public boolean isHorizontal(List<Point> stroke) {
        Point origin = stroke.get(0);

        for (Point point : stroke) {
            if (veersVertical(point, origin)) {
                return false;
            }
        }
        return true;
    }

    private boolean veersVertical(Point point, Point origin) {
        return point.getY() > origin.getY() + ERROR_ALLOWANCE || point.getY() < origin.getY() - ERROR_ALLOWANCE;
    }

    public boolean isVertical(List<Point> stroke) {
        Point origin = stroke.get(0);
        for (Point point : stroke) {
            if (veersHorizontal(point, origin)) {
                return false;
            }
        }
        return true;
    }

    private boolean veersHorizontal(Point point, Point origin) {
        return point.getX() > origin.getX() + ERROR_ALLOWANCE || point.getX() < origin.getX() - ERROR_ALLOWANCE;
    }
}
