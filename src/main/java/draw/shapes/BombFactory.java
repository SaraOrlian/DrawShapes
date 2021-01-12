package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * build ghosts
 */

public class BombFactory {
    private final int X_OFFSET = Bomb.RADIUS;
    private final int Y_OFFSET = Bomb.RADIUS + 10;
    private final ShapeFactory shapeFactory;
    private final Random RANDOM = new Random();

    public BombFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

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
        return new Point(RANDOM.nextInt(DrawShapesFrame.WIDTH - 2 * X_OFFSET) + X_OFFSET, getRandY());
    }


    private int getRandY() {
        int y = RANDOM.nextInt(DrawShapesFrame.HEIGHT);
        return y < DrawShapesFrame.HEIGHT - Y_OFFSET ? notTooHigh(y) : y - Y_OFFSET;

    }

    private int notTooHigh(int y) {
        return y < 35? y + 35 : y;
    }
}
