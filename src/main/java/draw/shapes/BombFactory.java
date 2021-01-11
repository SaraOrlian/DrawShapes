package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * build ghosts
 */

public class BombFactory {
    private final ShapeFactory shapeFactory = new ShapeFactory();
    private final Random random = new Random();

    public Bomb newInstance(int numShapes) {
        return new Bomb(getNewShapeQueue(numShapes), getRandomLocation());
    }

    private Queue<Shape> getNewShapeQueue(int numShapes) {
        Queue<Shape> shapeQueue = new LinkedList<>();
        for (int i = 0; i < numShapes; i++) {
            shapeQueue.add(shapeFactory.newInstance());
        }
        return shapeQueue;
    }

    private Point getRandomLocation() {
        int offset = 200;
        return new Point(random.nextInt(DrawShapesFrame.WIDTH - offset), random.nextInt(DrawShapesFrame.HEIGHT - offset) );
    }
}
