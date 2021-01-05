package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;

public class GhostFactory {

    ShapeFactory shapeFactory;

    public GhostFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Queue<Shape> newInstance(int numShapes) {
        Queue<Shape> ghost = new LinkedList<>();
        for(int i = 0; i < numShapes; i++) {
            ghost.add(shapeFactory.newInstance());
        }
        return ghost;
    }
}
