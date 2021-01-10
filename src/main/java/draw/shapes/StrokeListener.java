package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StrokeListener extends MouseInputAdapter {

    private final ShapesView shapesView;
    private final BombManager bombManager;
    private final List<Point> stroke = new ArrayList<>();
    private final ShapeAnalyzer shapeAnalyzer;

    public StrokeListener(BombManager bombManager, ShapesView shapesView, ShapeAnalyzer shapeAnalyzer) {
        this.bombManager = bombManager;
        this.shapesView = shapesView;
        this.shapeAnalyzer = shapeAnalyzer;
    }

    public void mouseDragged(MouseEvent e) {
        Point point = new draw.shapes.Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
        stroke.add(point);
        shapesView.addPoint(point);
        e.getComponent().repaint();
    }

    public void mouseReleased(MouseEvent e) {

        Shape drawing = shapeAnalyzer.getShape(stroke);
        bombManager.dequeueShape(drawing);
        stroke.clear();
        shapesView.clearDrawing();
        e.getComponent().repaint();
    }
}
