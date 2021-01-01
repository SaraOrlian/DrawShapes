package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Sara
public class DrawShapesFrame extends JFrame {


    MouseListener listener = new MouseListener();


    public DrawShapesFrame(ShapesView shapesView) {
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Draw Shapes");
        setLayout(new BorderLayout());
        JLabel lineType = listener.lineType;
        lineType.setText("blank");
        add(lineType);


    }

}
