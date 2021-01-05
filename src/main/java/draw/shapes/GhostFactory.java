package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;

public class GhostFactory {

    ShapeFactory shapeFactory = new ShapeFactory();

    public Queue<Shape> newInstance(int numShapes) {
        Queue<Shape> ghost = new LinkedList<>();
        for(int i = 0; i < numShapes; i++) {
            ghost.add(shapeFactory.newInstance());
        }
        return ghost;
    }
}
