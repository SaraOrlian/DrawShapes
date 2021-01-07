package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    private final GhostFactory ghostFactory = new GhostFactory();
    private List<Ghost> ghostList = new LinkedList<>();
    private boolean gameOver = false;
    static final Random random = new Random();
    public static final int GHOST_WIDTH = 400;
    public static final int GHOST_HEIGHT = 200;

    public GhostManager() {
        createGhost(1);
        createGhost(1);
        createGhost(1);
    }

    public void createGhost(int numShapes) {

        Point location;
        do {
            location = (new Point(random.nextInt(1000), random.nextInt(650) + 50));

        } while (isOccupied(location));

        ghostList.add(ghostFactory.newInstance(numShapes));
        ghostList.get(ghostList.size() - 1).setLocation(location);
    }

    private boolean isOccupied(Point location) {
        for (Ghost ghost : ghostList) {
            if ((location.getX() > ghost.getLocation().getX() && location.getX() < ghost.getLocation().getX() + GHOST_WIDTH)
                    && (location.getY() > ghost.getLocation().getY() && location.getY() < ghost.getLocation().getY() + GHOST_HEIGHT)) {
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
