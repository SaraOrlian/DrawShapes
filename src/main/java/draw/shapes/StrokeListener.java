package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Shana
public class StrokeListener extends MouseInputAdapter {

    private StrokeManager view;
    private List<Point> stroke = new ArrayList<>();

    public StrokeListener(StrokeManager view) {
        this.view = view;
    }

    public void mouseMoved(MouseEvent e) {
        Point point = new draw.shapes.Point((int)e.getPoint().getX(), (int)e.getPoint().getY());
        stroke.add(point);
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseReleased(MouseEvent e) {
        view.setStroke(stroke);
        stroke.clear();
        view.whichStroke();
    }
}
