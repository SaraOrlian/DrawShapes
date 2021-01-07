package draw.shapes;

import javax.swing.*;
import java.awt.*;

//Sara
public class DrawShapesFrame extends JFrame {

    public DrawShapesFrame(StrokeListener listener, ShapesView shapesView) {
        setSize(1000, 800);
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
