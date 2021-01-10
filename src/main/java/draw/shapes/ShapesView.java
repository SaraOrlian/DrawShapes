package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class ShapesView extends JComponent {
    private ArrayList<Point> drawing = new ArrayList<Point>();
    private GhostManager ghostManager;
    private int ghostXval;
    private int ghostYval;
    public static final int BOMB_DIAMETER = 130;
    public static int BOMB_BORDER_DIAMETER = 140;
    private final int BOMB_TOP_DIMENSION = 35;
    private final Color BOMB_COLOR = Color.BLACK;
    private final Color START_COLOR = new Color(10, 240, 120);
    private final Color SHINE_COLOR = Color.WHITE;

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
            paintShapes(g, ghost);
            paintBomb(g, ghost);

        }
    }

    private void paintBomb(Graphics g, Ghost ghost) {
        int bombOutlineX = ghostXval - 70;
        int bombOutlineY = ghostYval + 27;
        int bombX = ghostXval - 65;
        int bombY = ghostYval + 32;


        drawOutline(g, bombOutlineX, bombOutlineY, ghost.getAge());
        g.setColor(BOMB_COLOR);
        g.fillOval(bombX, bombY, BOMB_DIAMETER, BOMB_DIAMETER);

        int bombRadius = BOMB_BORDER_DIAMETER / 2;

        g.fillRect(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), ghostYval + 20, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION);
        g.fillOval(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), ghostYval + 10, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION / 2);

        g.setColor(SHINE_COLOR);
        g.fillArc(bombX + 25, bombY + 30, 10, 25, 50, 180);
    }

    private void drawOutline(Graphics g, int bombOutlineX, int bombOutlineY, float age) {
        g.setColor(getCurrentColor(age));
        g.fillOval(bombOutlineX, bombOutlineY, BOMB_BORDER_DIAMETER, BOMB_BORDER_DIAMETER);
    }

    private void paintShapes(Graphics g, Ghost ghost) {
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

    //chnage to take ghost object?
    private Color getCurrentColor(float age) {
        age *= 20;
        return new Color(START_COLOR.getRed() + (int) age, START_COLOR.getGreen() - (int) age, START_COLOR.getBlue());
    }
}
