package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static draw.shapes.BombManager.LIFESPAN;

public class ShapesView extends JComponent {
    private ArrayList<Point> drawing = new ArrayList<>();
    private BombManager bombManager;
    private int bombXval;
    private int bombYval;
    public static final int BOMB_DIAMETER = 130;
    public static int BOMB_BORDER_DIAMETER = 140;
    private final int BOMB_TOP_DIMENSION = 35;
    private final Color BOMB_COLOR = Color.BLACK;
    private final Color START_COLOR = Color.GREEN;
    private final Color SHINE_COLOR = Color.WHITE;

    public ShapesView(BombManager bombManager) {
        this.bombManager = bombManager;
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
        paintBombs(g);
    }

    private void paintBombs(Graphics g) {
        List<Bomb> bombList = bombManager.getBombList();
        for (Bomb bomb : bombList) {
            bombXval = bomb.getLocation().getX();
            bombYval = bomb.getLocation().getY();
            paintShapes(g, bomb);
            paintBomb(g, bomb);

        }
    }

    private void paintBomb(Graphics g, Bomb bomb) {
        if (bomb.getAge() >= LIFESPAN) {
            paintExplosion(g);
            return;
        }
        int bombOutlineX = bombXval - 85;
        int bombOutlineY = bombYval + 27;
        int bombX = bombXval - 80;
        int bombY = bombYval + 32;


        drawOutline(g, bombOutlineX, bombOutlineY, bomb.getAge());
        g.setColor(BOMB_COLOR);
        g.fillOval(bombX, bombY, BOMB_DIAMETER, BOMB_DIAMETER);

        int bombRadius = BOMB_BORDER_DIAMETER / 2;

        g.fillRect(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), bombYval + 20, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION);
        g.fillOval(bombOutlineX + (bombRadius - BOMB_TOP_DIMENSION / 2), bombYval + 10, BOMB_TOP_DIMENSION, BOMB_TOP_DIMENSION / 2);

        g.setColor(SHINE_COLOR);
        g.fillArc(bombX + 25, bombY + 30, 10, 25, 50, 180);
    }

    private void paintExplosion(Graphics g) {
        g.setColor(Color.RED);
        int[] xpoints = new int[] {
                bombXval,
                bombXval + 30,
                bombXval + 50,
                bombXval + 130,
                bombXval + 110,
                bombXval + 170,
                bombXval + 90,
                bombXval + 110,
                bombXval + 80,
                bombXval + 60,
                bombXval + 30,
                bombXval -40,
                bombXval -30,
                bombXval -90,
                bombXval -30,
                bombXval -50,
                bombXval
        };
        int[] ypoints = new int[] {
                bombYval - 20,
                bombYval - 70,
                bombYval - 20,
                bombYval -40,
                bombYval - 20,
                bombYval + 30,
                bombYval + 50,
                bombYval + 90,
                bombYval + 80,
                bombYval + 160,
                bombYval + 120,
                bombYval + 170,
                bombYval + 80,
                bombYval + 20,
                bombYval + 40,
                bombYval -40,
                bombYval - 20
        };
        int npoints = 17;
        g.fillPolygon(xpoints, ypoints, npoints);
    }

    private void drawOutline(Graphics g, int bombOutlineX, int bombOutlineY, float age) {
        g.setColor(getCurrentColor(age));
        g.fillOval(bombOutlineX, bombOutlineY, BOMB_BORDER_DIAMETER, BOMB_BORDER_DIAMETER);
    }

    private void paintShapes(Graphics g, Bomb bomb) {
        for (Shape shape : bomb.getShapeQueue()) {
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
        bombXval += 10;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(Math.toRadians(-90), bombXval, bombYval);
        g2d.drawString("\u2796", bombXval, bombYval+20);
        g2d.setTransform(orig);
        bombXval += 20;
    }

    private void drawVee(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.ORANGE);
        g.drawString("\u02c5", bombXval++, bombYval);
        bombXval += 30;
    }

    private void drawHorizontal(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        g.setColor(Color.BLUE);
        bombXval+=30;
        g.drawString("\u2796", bombXval-20, bombYval);
        bombXval+=10;
    }

    private void drawCarat(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 50));
        g.setColor(Color.GREEN);
        g.drawString("\u02c4", bombXval++, bombYval);
        bombXval += 30;
    }

    private void paintStroke(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (int i = 0; i < drawing.size() - 2; i++) {
                g2.drawLine(drawing.get(i).getX(), drawing.get(i).getY(), drawing.get(i + 1).getX(), drawing.get(i + 1).getY());
            }
    }


    private Color getCurrentColor(float age) {
    age *= (float)Color.RED.getRed()/(float) BombManager.LIFESPAN;
        return new Color(START_COLOR.getRed() + (int) age, START_COLOR.getGreen() - (int) age, 0);
    }
}
