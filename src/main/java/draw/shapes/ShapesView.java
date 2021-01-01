package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;

//Sara
public class ShapesView extends JComponent {

    private ShapeManager shapeManager = new ShapeManager();
    private LinkedList<String> list = shapeManager.getShapeList1();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawList(g, list);
    }

    private void drawList(Graphics g, LinkedList<String> list) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("",Font.PLAIN,30));
        Random rand = new Random();
        int randX = rand.nextInt(400);
        int randY = rand.nextInt(400);
        String listVal;
        for (int i = 0; i<list.size(); i++) {
            listVal = list.get(i);
            if (listVal == "|") {
                g.setColor(Color.BLUE);
                g.drawString("\u2796", randX+(i), randY);
                randX+=50;
            } else if (listVal == "\u2796") {
                g.setColor(Color.RED);
                randX+=10;
                Graphics2D g2d = (Graphics2D)g;
                AffineTransform orig = g2d.getTransform();
                g2d.rotate(Math.toRadians(-90), randX+(i), randY);
                g2d.drawString(listVal, randX+(i), randY);
                g2d.setTransform(orig);
                randX+=10;
                g.setColor(Color.ORANGE);
                g.drawString("\u26AA", randX+(i), randY);
                randX+=40;
            }


        }
    }
}
