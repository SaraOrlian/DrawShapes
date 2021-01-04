package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Shana
public class StrokeListener extends MouseInputAdapter {

    private StrokeManager strokeManager;
    private List<Point> stroke = new ArrayList<>();
    private boolean released = false;
    private Point point;

    public StrokeListener(StrokeManager strokeManager) {
        this.strokeManager = strokeManager;
    }

    public boolean isReleased() {
        return released;
    }

    public Point getPoint() {
        return point;
    }

    public void mouseDragged(MouseEvent e) {
        released = false;
        point = new draw.shapes.Point((int)e.getPoint().getX(), (int)e.getPoint().getY());
        stroke.add(point);
        e.getComponent().repaint();
    }

    public void mouseReleased(MouseEvent e) {
        released = true;
        e.getComponent().repaint();
        strokeManager.setStroke(stroke);
        stroke.clear();
        strokeManager.whichStroke();
    }
}
