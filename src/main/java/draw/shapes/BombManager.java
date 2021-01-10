package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class BombManager {
    public static final int LIFESPAN = 12;
    public static final int MAX_BOMBS = 10;
    private final List<Bomb> bombList = new LinkedList<>();


    public BombManager() {
    }

    public void createBomb(int numShapes) {
        BombFactory bombFactory = new BombFactory();
        Bomb newBomb;
        do {
            newBomb = bombFactory.newInstance(numShapes);
        } while (overlaps(newBomb));
        if(bombList.size() != MAX_BOMBS) {
            bombList.add(newBomb);
        }

    }

    private boolean overlaps(Bomb newBomb) {
        for (Bomb bomb : bombList) {
            if (bomb.intersects(newBomb)) {
                return true;
            }
        }
        return false;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void dequeueShape(Shape drawing) {
        if (!isGameOver()) {
            Iterator<Bomb> iterator = bombList.iterator();
            while (iterator.hasNext()) {
                Bomb bomb = iterator.next();
                if (bomb.getShapeQueue().peek() == drawing) {
                    bomb.shapeQueue.remove();
                }
                if (bomb.getShapeQueue().isEmpty()) {
                    iterator.remove();
                }
            }
        }
    }

    public boolean bombExploded() {
        Iterator<Bomb> iterator = bombList.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (bomb.getAge() > LIFESPAN) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return bombExploded();
    }

    public void clearBombs() {
        bombList.clear();
    }
}
