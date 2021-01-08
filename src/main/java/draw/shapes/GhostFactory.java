package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * build ghosts
 */

public class GhostFactory {
    private final int OFFSET = 200;
    private final ShapeFactory SHAPE_FACTORY = new ShapeFactory();
    private final Random RANDOM = new Random();

    public Ghost newInstance(int numShapes) {
        return new Ghost(getNewShapeQueue(numShapes), getRandomLocation());
    }

    private Queue<Shape> getNewShapeQueue(int numShapes) {
        Queue<Shape> shapeQueue = new LinkedList<>();
        for (int i = 0; i < numShapes; i++) {
            shapeQueue.add(SHAPE_FACTORY.newInstance());
        }
        return shapeQueue;
    }

    private Point getRandomLocation() {
        return new Point(RANDOM.nextInt(DrawShapesFrame.WIDTH - OFFSET), RANDOM.nextInt(DrawShapesFrame.HEIGHT - OFFSET) );
    }
}
