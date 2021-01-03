package draw.shapes;

import javax.swing.*;
import java.awt.*;

//Sara
public class DrawShapesFrame extends JFrame {

<<<<<<< HEAD
    public DrawShapesFrame(ShapesView shapesView) {
        setSize(1000, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw Shapes");
        setLayout(new BorderLayout());
        add(shapesView);
=======
    public DrawShapesFrame(StrokeListener listener, ShapesView shapesView) {
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw Shapes");
        setLayout(new BorderLayout());
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);

>>>>>>> 12133e3598022045d07c6acc867e8f574348ba3c
    }

}
