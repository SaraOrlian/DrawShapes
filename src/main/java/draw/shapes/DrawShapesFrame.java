package draw.shapes;

import javax.swing.*;
import java.awt.*;

//Sara
public class DrawShapesFrame extends JFrame {

    public DrawShapesFrame(ShapesView shapesView) {
        setSize(1000, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw Shapes");
        setLayout(new BorderLayout());
        add(shapesView);
    }

}
