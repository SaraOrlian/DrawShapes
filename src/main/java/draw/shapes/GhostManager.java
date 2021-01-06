package draw.shapes;


import java.util.*;

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
        Shape drawing = analyzer.strokeIsShape();
        Iterator<Queue<Shape>> iterator = ghostList.iterator();
        while(iterator.hasNext()) {
            Queue<Shape> ghost = iterator.next();
            if (ghost.peek() == drawing){
                ghost.remove();
            }
            if(ghost.isEmpty()) {
                iterator.remove();
            }
        }
    }
}
