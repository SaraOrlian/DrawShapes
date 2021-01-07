package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghost {

    private Point location;
    Queue<Shape> shapeQueue;

    public Ghost() {
        shapeQueue = new LinkedList<>();
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public Point getLocation(){
        return location;
    }

    public void setLocation(Point point) {
        this.location = point;
    }
}
