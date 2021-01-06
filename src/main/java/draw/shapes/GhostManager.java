package draw.shapes;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    ShapeAnalyzer analyzer;
    GhostFactory ghostFactory = new GhostFactory();
    List<Queue<Shape>> ghostList = new LinkedList<>();

    public GhostManager(ShapeAnalyzer analyzer) {
        this.analyzer = analyzer;
        createGhost(2);
        createGhost(2);
        createGhost(2);
        createGhost(2);
    }

    public void createGhost(int numShapes) {
        ghostList.add(ghostFactory.newInstance(numShapes));
    }

    public List<Queue<Shape>> getGhostList() {
        return ghostList;
    }

    public void dequeueShape() {
        //iterator so we can remove from list as we go thru it
        for (Queue<Shape> ghost : ghostList) {
            Shape drawing = analyzer.strokeIsShape();
            if (ghost.peek() != null && ghost.peek() == drawing) {
                ghost.remove();
            }
        }
    }
}
