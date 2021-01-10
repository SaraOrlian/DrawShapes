package draw.shapes;

import java.util.Timer;

public class BombThread extends Thread {

    private int delay;
    private int counter;
    private final int MIN_DELAY = 2000;
    private final int MAX_BOMBS = 10;
    private final int MAX_SHAPES = 6;
    private int numShapes;
    private int numBombs;
    private final BombManager bombManager;
    private final PaintTask paintTask ;

    private final int BOMB_INTERVAL = 5;
    private final int SHAPE_INTERVAL = 10;
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
        timer.schedule(paintTask, 0, 75);
        while (!bombManager.isGameOver()) {
            generateBombs();
            }
    }

    private void generateBombs() {
        while (!bombManager.isGameOver()) {

            if (bombManager.getBombList().size() != 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (bombManager.isGameOver()) {
                break;
            }

            for (int i = 0; i < numBombs; i++) {
                bombManager.createBomb(numShapes);
            }
            counter++;

            if (counter % BOMB_INTERVAL == 0) {

                if (numBombs < MAX_BOMBS) {
                    numBombs++;
                }
                if (delay > MIN_DELAY) {
                    delay -= 100;
                }
            }
            if (counter % SHAPE_INTERVAL == 0) {

                if (numShapes < MAX_SHAPES) {
                    numShapes++;
                }
                if (delay > MIN_DELAY) {
                    delay -= 50;
                }
            }
        }
    }
}
