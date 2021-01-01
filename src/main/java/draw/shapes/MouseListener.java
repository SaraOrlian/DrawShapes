package draw.shapes;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Shana
public class MouseListener extends MouseInputAdapter {
    ShapeAnalyzer analyzer = new ShapeAnalyzer();
    private List<Point> currentStroke = new ArrayList<>();
    //List<List<Point>> strokes = new ArrayList<>();
    JLabel lineType = new JLabel();

    public void mouseDragged(MouseEvent e) {

        Point point = new draw.shapes.Point(e.getX(), e.getY());
        currentStroke.add(point);
    }

    public void mouseReleased(MouseEvent e) {
        if (analyzer.isHorizontal(currentStroke)) {
            lineType.setText("Horizontal");
        } else {
            lineType.setText("Invalid");
        }
        currentStroke.clear();
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }



}
