package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    private final GhostFactory ghostFactory = new GhostFactory();
    private List<Ghost> ghostList = new LinkedList<>();
    private boolean gameOver = false;

    public GhostManager() {
        createGhost(1);
        createGhost(1);
        createGhost(1);
    }

    public void createGhost(int numShapes) {

        ghostList.add(ghostFactory.newInstance(numShapes));
    }

    public List<Ghost> getGhostList() {
        return ghostList;
    }

    public void dequeueShape(Shape drawing) {
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

    public boolean isGameOver() {
        return gameOver;
    }
}
