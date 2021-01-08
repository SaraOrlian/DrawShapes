package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StrokeListener extends MouseInputAdapter {

    private final ShapesView shapesView;
    private final GhostManager ghostManager;
    private final List<Point> stroke = new ArrayList<>();
    private final ShapeAnalyzer shapeAnalyzer;

    public StrokeListener(GhostManager ghostManager, ShapesView shapesView, ShapeAnalyzer shapeAnalyzer) {
        this.ghostManager = ghostManager;
        this.shapesView = shapesView;
        this.shapeAnalyzer = shapeAnalyzer;
    }

    public void mouseDragged(MouseEvent e) {
        if(ghostManager.isGameOver()) {
            shapesView.removeComponentListener((ComponentListener) this);
        }
        Point point = new draw.shapes.Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
        stroke.add(point);
        //System.out.println(point);
        shapesView.addPoint(point);
        e.getComponent().repaint();
    }

    public void mouseReleased(MouseEvent e) {

        Shape drawing = shapeAnalyzer.getShape(stroke);
        ghostManager.dequeueShape(drawing);
        stroke.clear();
        shapesView.clearDrawing();
        e.getComponent().repaint();
        //shapeAnalyzer.whichStroke();
    }
}
