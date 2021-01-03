package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//Sara
public class StrokeManager extends JComponent {

    ShapeAnalyzer analyzer;
    List<Point> stroke;
    List<Queue<Shape>> shapeQueues = new ArrayList<>();

    public StrokeManager(ShapeAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public List<Point> getStroke() {
        return stroke;
    }

    public void setStroke(List<Point> stroke) {
        this.stroke = new ArrayList<>(stroke);
    }

    public void dequeueShape() {
        for (Queue shapeQueue : shapeQueues) {
            if (strokeIsShape(stroke, (Shape) shapeQueue.peek())) {
                shapeQueue.remove();
            }
        }
    }

    private boolean strokeIsShape(List<Point> stroke, Shape shape) {
        switch (shape) {
            case UP_SLOPE -> {
                return analyzer.isUpSlope(stroke);
            }
            case DOWN_SLOPE -> {
                return analyzer.isDownSlope(stroke);
            }
            case HORIZONTAL -> {
                return analyzer.isHorizontal(stroke);
            }
            case VERTICAL -> {
                return analyzer.isVertical(stroke);
            }
            default -> {
                return false;
            }
        }
    }


    //testing
    public void whichStroke() {
        if (analyzer.isHorizontal(stroke)) {
            System.out.println("horizontal");
        } else if (analyzer.isVertical(stroke)) {
            System.out.println("vertical");
        } else if (analyzer.isDownSlope(stroke)) {
            System.out.println("down slope");
        } else if (analyzer.isUpSlope(stroke)) {
            System.out.println("up slope");
        } else {
            System.out.println("not recognized");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
