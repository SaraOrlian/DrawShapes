package draw.shapes;

import java.util.Timer;

public class BombThread extends Thread {

    private int delay;
    private int counter;
    public static final int MAX_BOMBS = 10;
    public static final int MAX_SHAPES = 6;
    private int numShapes;
    private int numBombs;
    private final BombManager bombManager;
    private final PaintTask paintTask;
    private final Timer timer;

    public BombThread(BombManager bombManager, PaintTask paintTask) {
        this.bombManager = bombManager;
        this.paintTask = paintTask;
        timer = new Timer();
        delay = 5000;
        numShapes = 1;
        numBombs = 3;
        bombManager.createBomb(1);
        bombManager.createBomb(1);
        bombManager.createBomb(1);
    }

    public void run() {
        timer.schedule(paintTask, 0, 60);
        generateBombs();
        //bombManager.explodeBomb();
    }

    private void generateBombs() {
        while (!bombManager.isGameOver()) {
            delayNewBombs();
            createBombs();
            counter++;
            adjustBombRate();
            adjustShapeRate();
        }
    }

    private void createBombs() {
        for (int i = 0; i < numBombs; i++) {
            bombManager.createBomb(numShapes);
        }
    }

    private void delayNewBombs() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void adjustShapeRate() {
        int shapeInterval = 10;
        if (counter % shapeInterval == 0) {
            incrementNumShapes();
            decreaseDelay(50);
        }
    }

    private void adjustBombRate() {
        int bombInterval = 5;
        if (counter % bombInterval == 0) {
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
        int minDelay = 2000;
        if (delay > minDelay) {
            delay -= change;
        }
    }
}
