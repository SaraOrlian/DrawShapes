package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    ShapeAnalyzer analyzer;
    GhostFactory ghostFactory = new GhostFactory();
    List<Ghost> ghostList = new LinkedList<>();

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

    public List<Ghost> getGhostList() {
        return ghostList;
    }

    public void dequeueShape() {
        Shape drawing = analyzer.strokeIsShape();
        Iterator<Ghost> iterator = ghostList.iterator();
        while(iterator.hasNext()) {
            Ghost ghost = iterator.next();
            if (ghost.getShapeQueue().peek() == drawing){
                ghost.shapeQueue.remove();
            }
            if(ghost.getShapeQueue().isEmpty()) {
                iterator.remove();
            }
        }
    }
}
