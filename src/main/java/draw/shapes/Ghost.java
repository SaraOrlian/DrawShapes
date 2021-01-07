package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghost {

    private Point location;
//    static final Random random = new Random();
    Queue<Shape> shapeQueue;

    public Ghost() {
        //xVal = random.nextInt(400);
        //yVal = random.nextInt(400);
        shapeQueue = new LinkedList<>();
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public Point getLocation(){
        return new Point(location.getX(),location.getY());
    }

    public void setLocation(Point point) {
        this.location = point;
    }
}
