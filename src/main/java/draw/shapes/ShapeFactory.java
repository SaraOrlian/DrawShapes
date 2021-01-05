package draw.shapes;

import java.util.Random;

public class ShapeFactory {
    private final Random random = new Random();

    public Shape newInstance() {
        return Shape.values()[random.nextInt(Shape.values().length)];
    }
}
