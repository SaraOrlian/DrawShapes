package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Shana
public class StrokeListener extends MouseInputAdapter {

    private GhostManager ghostManager;
    private List<Point> stroke = new ArrayList<>();
    private boolean released = false;
    private Point point;

    public StrokeListener(GhostManager ghostManager) {
        this.ghostManager = ghostManager;
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

        ghostManager.setStroke(stroke);

        ghostManager.whichStroke();
        e.getComponent().repaint();
        stroke.clear();
    }
}
