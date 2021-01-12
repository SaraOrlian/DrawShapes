package draw.shapes;

import javax.swing.*;
import java.awt.*;


public class DrawShapesFrame extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 900;

    public DrawShapesFrame(StrokeListener listener, ShapesView shapesView) {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diffusion");
        add(shapesView);
        shapesView.addMouseListener(listener);
        shapesView.addMouseMotionListener(listener);
    }
}
