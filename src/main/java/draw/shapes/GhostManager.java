package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class GhostManager {
    public static final int LIFESPAN = 10;
    private final GhostFactory ghostFactory = new GhostFactory();
    private List<Ghost> ghostList = new LinkedList<>();
    private boolean gameOver = false;
    


    public GhostManager() {
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
        if(!isGameOver()) {
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
    }

    public void exploded() {
        Iterator<Ghost> iterator = ghostList.iterator();
        while (iterator.hasNext()) {
            Ghost ghost = iterator.next();
            long currentTime = System.currentTimeMillis()/1000;
            if(currentTime - ghost.getBirthday() > LIFESPAN) {
                System.out.println("GAME OVER");
                gameOver = true;
                return;
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
