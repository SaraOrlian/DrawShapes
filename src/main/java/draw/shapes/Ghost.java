package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghost {

    private int xVal;
    private int yVal;
    Queue<Shape> shapeQueue;

    public Ghost() {
        xVal = new Random().nextInt(400);
        yVal = new Random().nextInt(400);
        shapeQueue = new LinkedList<>();
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public int getxVal() {
        return xVal;
    }

    public int getyVal() {
        return yVal;
    }
}
