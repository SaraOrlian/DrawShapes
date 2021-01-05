package draw.shapes;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Sara
public class GhostManager {
    ShapeAnalyzer analyzer;
    List<Point> stroke;
    GhostFactory ghostFactory = new GhostFactory();
    List<Queue<Shape>> ghostList = new LinkedList<>();



    public GhostManager(ShapeAnalyzer analyzer) {
        this.analyzer = analyzer;
        createGhost(4);
    }

    public void setStroke(List<Point> stroke) {
        this.stroke = new ArrayList<>(stroke);
    }

    public void createGhost(int numShapes) {
        ghostList.add(ghostFactory.newInstance(numShapes));
    }

    public List<Queue<Shape>> getGhostList() {

        return ghostList;
    }


    public void dequeueShape() {
        for (Queue<Shape> ghost : ghostList) {
            if (ghost.peek() != null && strokeIsShape(stroke, (Shape) ghost.peek())) {
                ghost.remove();
            }
        }
    }

    private boolean strokeIsShape(List<Point> stroke, Shape shape) {
        boolean strokeIsShape;
        switch (shape) {
            case CARAT :
                strokeIsShape = analyzer.isCarat(stroke);
                break;
            case VEE :
                strokeIsShape =  analyzer.isVee(stroke);
                break;
            case HORIZONTAL :
                strokeIsShape =  analyzer.isHorizontal(stroke);
                break;
            case VERTICAL :
                strokeIsShape =  analyzer.isVertical(stroke);
                break;
            default :
                strokeIsShape =  false;
                break;
        }
        return strokeIsShape;
    }






    //testing
    public void whichStroke() {
        if (analyzer.isHorizontal(stroke)) {
            System.out.println("horizontal");
        }
        if (analyzer.isVertical(stroke)) {
            System.out.println("vertical");
        }
        if (analyzer.isVee(stroke)) {
            System.out.println("v");
        }
        if (analyzer.isCarat(stroke)) {
            System.out.println("^");
        }
    }
}
