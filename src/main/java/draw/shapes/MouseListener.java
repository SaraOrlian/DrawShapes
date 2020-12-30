package draw.shapes;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Shana
public class MouseListener extends MouseInputAdapter {

    List<Point> stroke = new ArrayList<>();

    public void mouseDragged(MouseEvent e) {
        Point point = new draw.shapes.Point(e.getX(), e.getY());
        stroke.add(point);
    }

    public void mouseReleased(MouseEvent e) {
        //return array
        stroke.clear();
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
