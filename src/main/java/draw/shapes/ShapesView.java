package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static draw.shapes.Shape.*;

public class ShapesView extends JComponent {
    ArrayList<Point> drawing = new ArrayList<Point>();
    GhostManager ghostManager;
    Random random = new Random();

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
        List<Queue<Shape>> ghostList = ghostManager.getGhostList();

        for (Queue<Shape> ghost : ghostList) {
            int randX = random.nextInt(400);
            int randY = random.nextInt(400);

            for (Shape shape : ghost) {
                switch (shape) {
                    case CARAT:
                        g.setFont(new Font("", Font.PLAIN, 50));
                        g.setColor(Color.GREEN);
                        g.drawString("\u02c4", randX++, randY);
                        randX += 30;
                        break;
                    case VEE:
                        g.setFont(new Font("", Font.PLAIN, 50));
                        g.setColor(Color.ORANGE);
                        g.drawString("\u02c5", randX++, randY);
                        randX += 30;
                        break;
                    case HORIZONTAL:
                        g.setFont(new Font("", Font.PLAIN, 30));
                        g.setColor(Color.BLUE);
                        g.drawString("\u2796", randX++, randY);
                        randX += 50;
                        break;
                    case VERTICAL:
                        g.setFont(new Font("", Font.PLAIN, 30));
                        g.setColor(Color.RED);
                        randX += 10;
                        Graphics2D g2d = (Graphics2D) g;
                        AffineTransform orig = g2d.getTransform();
                        g2d.rotate(Math.toRadians(-90), randX++, randY);
                        g2d.drawString("\u2796", randX++, randY);
                        g2d.setTransform(orig);
                        randX += 10;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void paintStroke(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 0; i < drawing.size() - 2; i++) {
            g2.drawLine(drawing.get(i).getX(), drawing.get(i).getY(), drawing.get(i + 1).getX(), drawing.get(i + 1).getY());
        }
    }
}
