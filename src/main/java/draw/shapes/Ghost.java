package draw.shapes;

import java.util.Queue;

public class Ghost {

    private Point location;
    Queue<Shape> shapeQueue;
    private final int RADIUS = ShapesView.BOMB_BORDER_DIAMETER + 40;

    //pass list of shapes and location in constructor
    public Ghost(Queue<Shape> shapeQueue, Point location) {
        this.shapeQueue = shapeQueue;
        this.location = location;
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point point) {
        this.location = point;
    }

    public boolean intersects(Ghost other) {
        return this.getLocation().getDistance(other.getLocation()) < RADIUS;
    }
}
