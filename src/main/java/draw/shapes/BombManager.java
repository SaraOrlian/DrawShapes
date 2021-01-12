package draw.shapes;


import java.util.*;

//Get shape drawn and remove shape from ghost
public class BombManager {
    public static final int LIFESPAN = 12;
    public static final int MAX_BOMBS = 10;
    private final List<Bomb> bombList = new LinkedList<>();
    private ExplosionListener explosionListener;
    private BombFactory bombFactory;

    public BombManager(BombFactory bombFactory) {
        this.bombFactory = bombFactory;
    }

    public void setExplosionListener(ExplosionListener explosionListener) {
        this.explosionListener = explosionListener;

    }

    public void createBomb(int numShapes) {
        Bomb newBomb;
        do {
            newBomb = bombFactory.newInstance(numShapes);
        } while (overlaps(newBomb));
        if (bombList.size() != MAX_BOMBS) {
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
        Iterator<Bomb> iterator = bombList.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (bomb.getShapeQueue().peek() == drawing) {
                bomb.removeShape();
            }
            if (bomb.getShapeQueue().isEmpty()) {
                iterator.remove();
            }
        }
    }

    public void explodeBomb() {
        explosionListener.onExplosion();
    }

    public boolean isGameOver() {
        Iterator<Bomb> iterator = bombList.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (bomb.getAge() >= LIFESPAN) {
                return true;
            }
        }
        return false;
    }

    public void clearBombs() {
        bombList.clear();
    }
}
