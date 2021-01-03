package draw.shapes;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;

public class ShapesView extends JComponent {

    private ShapeManager shapeManager = new ShapeManager();
    private Queue<Shape> list; //not set to anything- where are the lists kept?

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawList(g);
    }

    private void drawList(Graphics g) {
        g.setFont(new Font("", Font.PLAIN, 30));
        //Generate random coords for displaying list randomly
        Random rand = new Random();
        int randX = rand.nextInt(400);
        int randY = rand.nextInt(400);
        //iterate through queue and display list
        Iterator iterator = list.iterator();
        Shape listVal;
        while (iterator.hasNext()) {
            listVal = (Shape) iterator.next();
            switch (listVal) {
                case UP_SLOPE -> {
                    g.setColor(Color.ORANGE);
                    g.drawString(listVal.toString(), randX++, randY);
                    break;
                }
                case DOWN_SLOPE -> {
                    g.setColor(Color.MAGENTA);
                    g.drawString(listVal.toString(), randX++, randY);
                    break;
                }
                case HORIZONTAL -> {
                    g.setColor(Color.RED);
                    randX += 10;
                    Graphics2D g2d = (Graphics2D) g;
                    AffineTransform orig = g2d.getTransform();
                    g2d.rotate(Math.toRadians(-90), randX++, randY);
                    g2d.drawString(listVal.toString(), randX++, randY);
                    g2d.setTransform(orig);
                    randX += 10;
                    break;
                }
                case VERTICAL -> {
                    g.setColor(Color.BLUE);
                    g.drawString(listVal.toString(), randX++, randY);
                    randX += 50;
                    break;
                }
                default -> {
                    return;
                }
            }
        }
    }
}
