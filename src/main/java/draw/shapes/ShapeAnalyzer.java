package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {
    //private List<Point> stroke;
    private final int ERROR_ALLOWANCE = 5;

    public boolean isHorizontal(List<Point> stroke) {
         return startsAtLeft(stroke) ? isLeftRightLine(stroke) : isRightLeftLine(stroke);
    }

    public boolean isVertical(List<Point> stroke) {
        return startsAtTop(stroke) ? isTopDownLine(stroke) : isBottomUpLine(stroke);
    }

    public boolean isUpSlope(List<Point> stroke) {
        return startsAtTop(stroke) ? isDownLeft(stroke) : isUpRight(stroke);
    }

    public boolean isDownSlope(List<Point> stroke) {
        return startsAtTop(stroke) ? isDownRight(stroke) : isUpLeft(stroke);
    }

    public boolean isV(List<Point> stroke) {
        //combine down right and  up right OR down left and upleft
        return false;
    }

    public boolean isCarat(List<Point> stroke) {
        //combine up right and  down left OR up left and down right
        return false;
    }

    private boolean startsAtLeft(List<Point> stroke) {
        return stroke.get(0).getX() < stroke.get(1).getX();
    }

    private boolean startsAtTop(List<Point> stroke) {
        return stroke.get(0).getY() < stroke.get(1).getY();
    }

    private boolean isUpRight(List<Point> stroke) {
        Point origin = stroke.get(0);
        Point idealPoint = new Point(origin.getX(), origin.getY());

        for (Point point : stroke) {
            if (veersVertical(point, idealPoint) || veersHorizontal(point, idealPoint)) {
                return false;
            }

            idealPoint.incrementX();
            idealPoint.decrementY();
        }
        return true;
    }

    private boolean isDownLeft(List<Point> stroke) {
        Point origin = stroke.get(0);
        Point idealPoint = new Point(origin.getX(), origin.getY());

        for (Point point : stroke) {
            if (veersVertical(point, idealPoint) || veersHorizontal(point, idealPoint)) {
                return false;
            }

            idealPoint.decrementX();
            idealPoint.incrementY();
        }
        return true;
    }

    private boolean isDownRight(List<Point> stroke) {
        Point origin = stroke.get(0);
        Point idealPoint = new Point(origin.getX(), origin.getY());

        for (Point point : stroke) {
            if (veersVertical(point, idealPoint) || veersHorizontal(point, idealPoint)) {
                return false;
            }

            idealPoint.incrementX();
            idealPoint.incrementY();
        }
        return true;
    }

    private boolean isUpLeft(List<Point> stroke) {
        Point origin = stroke.get(0);
        Point idealPoint = new Point(origin.getX(), origin.getY());

        for (Point point : stroke) {
            if (veersVertical(point, idealPoint) || veersHorizontal(point, idealPoint)) {
                return false;
            }

            idealPoint.decrementX();
            idealPoint.decrementY();
        }
        return true;
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
