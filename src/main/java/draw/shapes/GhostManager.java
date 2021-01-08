package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    private final GhostFactory ghostFactory = new GhostFactory();
    private List<Ghost> ghostList = new LinkedList<>();
    private boolean gameOver = false;
    static final Random random = new Random();
    public static final int GHOST_WIDTH = 600;
    public static final int GHOST_HEIGHT = 600;

    public GhostManager() {
        createGhost(1);
        createGhost(1);
        createGhost(1);
    }

    public void createGhost(int numShapes) {
        Ghost newGhost;
        do {
            newGhost = ghostFactory.newInstance(numShapes);
        } while (overlaps(newGhost));
        ghostList.add(newGhost);
    }

    private boolean overlaps(Ghost newGhost) {
        for (Ghost ghost : ghostList) {
            if (ghost.intersects(newGhost)) {
                return true;
            }
        }
        return false;
    }

    public List<Ghost> getGhostList() {
        return ghostList;
    }

    public void dequeueShape(Shape drawing) {
        Iterator<Ghost> iterator = ghostList.iterator();
        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();
            if (ghost.getShapeQueue().peek() == drawing) {
                ghost.shapeQueue.remove();
            }
            if (ghost.getShapeQueue().isEmpty()) {
                iterator.remove();
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
