package draw.shapes;

import java.util.Timer;

public class BombThread extends Thread {

    private long delay;
    private int counter;
    private final int MIN_DELAY = 2000;
    private final int MAX_BOMBS = 10;
    private final int MAX_SHAPES = 6;
    private int numShapes;
    private int numBombs;
    private final BombManager bombManager;
    private final ShapesView view;
    private long generationTime;

    private final int BOMB_INTERVAL = 5;
    private final int SHAPE_INTERVAL = 10;


    public BombThread(BombManager bombManager, ShapesView view) {
        this.bombManager = bombManager;
        this.view = view;
        delay = 5000;
        numShapes = 1;
        numBombs = 3;
    }

    public void run() {
        generateBombs();
        view.repaint();
        bombManager.explodeBomb();
    }

    private void generateBombs() {
        while (!bombManager.isGameOver()) {
            view.repaint();

            if (getElapsedTime() > delay) {
                createBombs();
                counter++;
                adjustBombRate();
                adjustShapeRate();
            }
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private long getElapsedTime() {
        return System.currentTimeMillis()  - generationTime;
    }

    private void createBombs() {
        for (int i = 0; i < numBombs; i++) {
            bombManager.createBomb(numShapes);
        }
        generationTime = System.currentTimeMillis();
    }

    private void delayNewBombs() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void adjustShapeRate() {
        if (counter % SHAPE_INTERVAL == 0) {
            incrementNumShapes();
            decreaseDelay(50);
        }
    }

    private void adjustBombRate() {
        if (counter % BOMB_INTERVAL == 0) {
            incrementNumBombs();
            decreaseDelay(100);
        }
    }

    private void incrementNumShapes() {
        if (numShapes < MAX_SHAPES) {
            numShapes++;
        }
    }

    private void incrementNumBombs() {
        if (numBombs < MAX_BOMBS) {
            numBombs++;
        }
    }

    private void decreaseDelay(int change) {
        if (delay > MIN_DELAY) {
            delay -= change;
        }
    }
}
