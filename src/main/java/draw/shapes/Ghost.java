package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghost {

    private Point location;
    Queue<Shape> shapeQueue;
    private final int RADIUS = 100;

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
