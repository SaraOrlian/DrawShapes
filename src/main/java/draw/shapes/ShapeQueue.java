package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;

public class ShapeQueue {

    Queue<Shape> shapesQueue = new LinkedList<>();

    public Queue<Shape> getShapesQueue() {
        return shapesQueue;
    }

    public void addShape(){
        shapesQueue.add(Shape.randomShape());
    }



}
