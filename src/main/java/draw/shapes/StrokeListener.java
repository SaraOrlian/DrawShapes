package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StrokeListener extends MouseInputAdapter {

    private final ShapesView view;
    private final BombManager manager;
    private final List<Point> stroke = new ArrayList<>();
    private final ShapeAnalyzer analyzer;

    public StrokeListener(BombManager bombManager, ShapesView shapesView, ShapeAnalyzer shapeAnalyzer) {
        this.manager = bombManager;
        this.view = shapesView;
        this.analyzer = shapeAnalyzer;
    }

    public void mouseDragged(MouseEvent e) {
        Point point = new draw.shapes.Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
        stroke.add(point);
        view.addPoint(point);
        e.getComponent().repaint();
    }

    public void mouseReleased(MouseEvent e) {

        Shape drawing = analyzer.getShape(stroke);
        manager.dequeueShape(drawing);
        stroke.clear();
        view.clearDrawing();
        e.getComponent().repaint();
    }
}
