package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {
    //private List<Point> stroke;
    private final int ERROR_ALLOWANCE = 5;

    public boolean isHorizontal(List<Point> stroke) {
        boolean leftRight = stroke.get(0).getX() < stroke.get(1).getX();
        if (leftRight) {
            return isLeftRightLine(stroke);
        } else {
            return isRightLeftLine(stroke);
        }
    }

    public boolean isVertical(List<Point> stroke) {
        boolean topDown = stroke.get(0).getY() < stroke.get(1).getY();
        if (topDown) {
            return isTopDownLine(stroke);
        } else {
            return isBottomUpLine(stroke);
        }
    }

    private boolean isLeftRightLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int maxX = 0;
        for (int i = 1; i < stroke.size(); i++) {
            if (veersVertical(stroke.get(i), origin)) {
                return false;
            }
            if (stroke.get(i).getX() < maxX) {
                return false;
            } else {
                maxX = stroke.get(i).getX();
            }
        }
        return true;
    }

    private boolean isRightLeftLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int minX = 0;
        for (int i = 1; i < stroke.size(); i++) {

            if (veersVertical(stroke.get(i), origin)) {
                return false;
            } else {
                if (stroke.get(i).getX() > minX) {
                    return false;
                } else {
                    minX = stroke.get(i).getY();
                }
            }
        }
        return true;
    }

    private boolean isBottomUpLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int minY = 0;
        for (int i = 1; i < stroke.size(); i++) {

            if (veersHorizontal(stroke.get(i), origin)) {
                return false;
            } else {
                if (stroke.get(i).getY() > minY) {
                    return false;
                } else {
                    minY = stroke.get(i).getY();
                }
            }
        }
        return true;
    }

    private boolean isTopDownLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int maxY = 0;
        for (int i = 1; i < stroke.size(); i++) {
            if (veersHorizontal(stroke.get(i), origin)) {
                return false;
            }
            if (stroke.get(i).getY() < maxY) {
                return false;
            } else {
                maxY = stroke.get(i).getY();
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
