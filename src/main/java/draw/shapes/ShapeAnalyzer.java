package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {
    //private List<Point> stroke;
    private final int ERROR_ALLOWANCE = 5;

    public boolean isHorizontal(List<Point> stroke) {
        boolean startsAtLeft = stroke.get(0).getX() < stroke.get(1).getX();
        return startsAtLeft ? isLeftRightLine(stroke) : isRightLeftLine(stroke);
    }

    public boolean isVertical(List<Point> stroke) {
        boolean startsAtTop = stroke.get(0).getY() < stroke.get(1).getY();
        return startsAtTop ? isTopDownLine(stroke) : isBottomUpLine(stroke);
    }

    private boolean isLeftRightLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int maxX = 0;
        for (Point point : stroke) {
            if (veersVertical(point, origin)) {
                return false;
            }
            if (point.getX() < maxX) {
                return false;
            } else {
                maxX = point.getX();
            }
        }
        return true;
    }

    private boolean isRightLeftLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int minX = 0;
        for (Point point : stroke) {
            if (veersVertical(point, origin)) {
                return false;
            }
            if (point.getX() > minX) {
                return false;
            } else {
                minX = point.getY();
            }
        }
        return true;
    }

    private boolean isBottomUpLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int minY = 0;
        for (Point point : stroke) {
            if (veersHorizontal(point, origin)) {
                return false;
            }
            if (point.getY() > minY) {
                return false;
            } else {
                minY = point.getY();
            }
        }
        return true;
    }

    private boolean isTopDownLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int maxY = 0;
        for (Point point : stroke) {
            if (veersHorizontal(point, origin)) {
                return false;
            }
            if (point.getY() < maxY) {
                return false;
            } else {
                maxY = point.getY();
            }
        }
        return true;
    }

    private boolean veersVertical(Point point, Point origin) {
        return veersDown(point, origin) || veersUp(point, origin);
    }

    private boolean veersHorizontal(Point point, Point origin) {
        return veersRight(point, origin) || veersLeft(point, origin);
    }

    private boolean veersUp(Point point, Point origin) {
        return point.getY() < origin.getY() - ERROR_ALLOWANCE;
    }

    private boolean veersDown(Point point, Point origin) {
        return point.getY() > origin.getY() + ERROR_ALLOWANCE;
    }

    private boolean veersLeft(Point point, Point origin) {
        return point.getX() < origin.getX() - ERROR_ALLOWANCE;
    }

    private boolean veersRight(Point point, Point origin) {
        return point.getX() > origin.getX() + ERROR_ALLOWANCE;
    }
}
