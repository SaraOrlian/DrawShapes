package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShapesView extends JComponent {
    StrokeListener listener;
    ArrayList<Point> drawing = new ArrayList<Point>();

    public ShapesView(StrokeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintStroke(g);
    }

    private void paintStroke(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        Point point = listener.getPoint();
        if (listener.isReleased()) {
            drawing.clear();
        } else {
            if (point != null) {
                drawing.add(point);
                for (int i = 0; i < drawing.size() -2; i++) {
                    g2.drawLine(drawing.get(i).getX(), drawing.get(i).getY(), drawing.get(i + 1).getX(), drawing.get(i + 1).getY());
                }
            }
        }
    }
}
