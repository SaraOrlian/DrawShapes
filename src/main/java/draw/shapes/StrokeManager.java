package draw.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Sara
public class StrokeManager extends JComponent {

    GhostManager ghostManager;
    ShapeAnalyzer analyzer;
    List<Point> stroke;
    List<Queue<Shape>> ghosts;


    public StrokeManager(ShapeAnalyzer analyzer, GhostManager ghostManager) {
        this.analyzer = analyzer;
        this.ghostManager = ghostManager;
        ghosts = new LinkedList<>(ghostManager.getGhostList());
    }

    public void setStroke(List<Point> stroke) {
        this.stroke = new ArrayList<>(stroke);
    }


    public void dequeueShape() {
        for (Queue<Shape> ghost : ghosts) {
            if (ghost.peek() != null && strokeIsShape(stroke, (Shape) ghost.peek())) {
                ghost.remove();
            }
        }
    }

    private boolean strokeIsShape(List<Point> stroke, Shape shape) {
        switch (shape) {
//            case UP_SLOPE -> {
//                return analyzer.isUpSlope(stroke);
//            }
//            case DOWN_SLOPE -> {
//                return analyzer.isDownSlope(stroke);
//            }
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
        } else if (analyzer.isV(stroke)) {
            System.out.println("v");
        } else if (analyzer.isCarat(stroke)) {
            System.out.println("^");
        } else {
            System.out.println("not recognized");
        }
    }
}
