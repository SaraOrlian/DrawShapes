package draw.shapes;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point invert() {
        int newX = y;
        int newY = x;
        return new Point(newX, newY);
    }

    public double getAngleBetween(Point other) {
        double opposite = Math.abs(this.getY() - other.getY());
        double adjacent = Math.abs(this.getX() - other.getX());
        return Math.atan(opposite / adjacent);
    }

    public double getDistance(Point other) {
        double xDiff = this.getX() - other.getX();
        double yDiff = this.getY() - other.getY();
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
