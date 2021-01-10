package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StrokeListener extends MouseInputAdapter {

    private final ShapesView VIEW;
    private final BombManager MANAGER;
    private final List<Point> STROKE = new ArrayList<>();
    private final ShapeAnalyzer ANALYZER;

    public StrokeListener(BombManager bombManager, ShapesView shapesView, ShapeAnalyzer shapeAnalyzer) {
        this.MANAGER = bombManager;
        this.VIEW = shapesView;
        this.ANALYZER = shapeAnalyzer;
    }

    public void mouseDragged(MouseEvent e) {
        Point point = new draw.shapes.Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
        STROKE.add(point);
        VIEW.addPoint(point);
        e.getComponent().repaint();
    }

    public void mouseReleased(MouseEvent e) {

        Shape drawing = ANALYZER.getShape(STROKE);
        MANAGER.dequeueShape(drawing);
        STROKE.clear();
        VIEW.clearDrawing();
        e.getComponent().repaint();
    }
}
