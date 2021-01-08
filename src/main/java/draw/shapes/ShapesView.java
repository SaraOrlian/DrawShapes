package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class ShapesView extends JComponent {
    ArrayList<Point> drawing = new ArrayList<Point>();
    GhostManager ghostManager;
    int ghostXval;
    int ghostYval;

    public ShapesView(GhostManager ghostManager) {
        this.ghostManager = ghostManager;
    }

    public void clearDrawing() {
        drawing.clear();
    }

    public void addPoint(Point point) {
        drawing.add(point);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintStroke(g);
        paintGhosts(g);
    }

    private void paintGhosts(Graphics g) {
        List<Ghost> ghostList = ghostManager.getGhostList();
        for (Ghost ghost : ghostList) {
            ghostXval = ghost.getLocation().getX();
            ghostYval = ghost.getLocation().getY();
            for (Shape shape : ghost.getShapeQueue()) {
                switch (shape) {
                    case CARAT:
                        drawCarat(g);
                        break;
                    case VEE:
                        drawVee(g);
                        break;
                    case HORIZONTAL:
                        drawHorizontal(g);
                        break;
                    case VERTICAL:
                        drawVertical(g);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void drawVertical(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        g.setColor(Color.RED);
        ghostXval += 10;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(Math.toRadians(-90), ghostXval++, ghostYval);
        g2d.drawString("\u2796", ghostXval++, ghostYval);
        g2d.setTransform(orig);
        ghostXval += 10;
    }

    private void drawVee(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.ORANGE);
        g.drawString("\u02c5", ghostXval++, ghostYval);
        ghostXval += 30;
    }

    private void drawHorizontal(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        g.setColor(Color.BLUE);
        g.drawString("\u2796", ghostXval++, ghostYval);
        ghostXval += 50;
    }

    private void drawCarat(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.GREEN);
        g.drawString("\u02c4", ghostXval++, ghostYval);
        ghostXval += 30;
    }

    private void paintStroke(Graphics g) {
        if (!ghostManager.isGameOver()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < drawing.size() - 2; i++) {
                g2.drawLine(drawing.get(i).getX(), drawing.get(i).getY(), drawing.get(i + 1).getX(), drawing.get(i + 1).getY());
            }
        }
    }
}
