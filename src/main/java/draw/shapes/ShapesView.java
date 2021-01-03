package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Sara
public class ShapesView extends JComponent {

    ShapeAnalyzer analyzer;

    public ShapesView(ShapeAnalyzer analyzer){
        this.analyzer = analyzer;

    }

    List<Point> stroke;

    public List<Point> getStroke() {
        return stroke;
    }

    public void setStroke(List<Point> stroke) {
        this.stroke = new ArrayList<>(stroke);
    }

    public void whichStroke(){
        if(analyzer.isHorizontal(stroke)) {
            System.out.println("horizontal");
            stroke.clear();
        } else if (analyzer.isVertical(stroke)) {
            System.out.println("vertical");
            stroke.clear();
        } else {
            System.out.println("not recognized");
            stroke.clear();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
