package draw.shapes;

import java.util.ArrayList;
import java.util.List;

public class ShapeReducer {

    private static final double FORTY_FIVE_DEGREES = Math.PI / 3;

    public List<Point> smooth(List<Point> stroke) {
        Point lastPoint =stroke.get(stroke.size()-1);

        List<Point> smoothStroke = new ArrayList<Point>();

        for (int i = 0; i < stroke.size() - 2; i += 3) {

            Point a = stroke.get(i);
            Point b = stroke.get(i + 1);
            Point c = stroke.get(i + 2);
            double aBAngle = a.getAngleBetween(b);
            double bCAngle = b.getAngleBetween(c);
            smoothStroke.add(a);
            if (Math.abs(aBAngle - bCAngle) > FORTY_FIVE_DEGREES) {
                smoothStroke.add(b);
            }
            smoothStroke.add(c);
        }
        smoothStroke.add(lastPoint);

        return smoothStroke;
    }
}
