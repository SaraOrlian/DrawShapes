package draw.shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Shape {
    HORIZONTAL, VERTICAL, UP_SLOPE, DOWN_SLOPE;

    private static final List<Shape> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Shape randomShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
