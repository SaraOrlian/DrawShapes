package draw.shapes;

import javax.swing.*;
import java.awt.*;

//Sara
public class DrawShapesFrame extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 1000;

    public DrawShapesFrame(StrokeListener listener, ShapesView shapesView) {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw Shapes");
        setLayout(new BorderLayout());
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        add(shapesView, BorderLayout.CENTER);
        shapesView.addMouseListener(listener);
        shapesView.addMouseMotionListener(listener);


    }

}
