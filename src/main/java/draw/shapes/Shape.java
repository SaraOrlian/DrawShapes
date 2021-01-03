package draw.shapes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Shape {
    HORIZONTAL("\u2796"), VERTICAL("\u2796") /*have to discuss, bc "-" is small, so planned to rotate the horizontal symbol*/
    , UP_SLOPE("/"), DOWN_SLOPE("\\");

    private static final List<Shape> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    Shape(String s) {

    }

    public static Shape randomShape() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
