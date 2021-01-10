package draw.shapes;

import javax.swing.*;
import java.util.Timer;

public class GhostThread extends Thread {

    private int delay = 5000;
    private int counter;
    private final int MIN_DELAY = 2000;
    private final int MAX_GHOSTS = 10;
    private final int MAX_SHAPES = 6;
    private int numShapes = 1;
    private int numGhosts = 3;
    private final GhostManager ghostManager;
    private final PaintTask paintTask ;

    private final int GHOST_INTERVAL = 5;
    private final int SHAPE_INTERVAL = 10;
    private final Timer timer = new Timer();

    public GhostThread(GhostManager ghostManager, PaintTask paintTask) {
        this.ghostManager = ghostManager;
        this.paintTask = paintTask;
        ghostManager.createGhost(1);
        ghostManager.createGhost(1);
        ghostManager.createGhost(1);
    }

    public void run() {
        timer.schedule(paintTask, 0, 100);
        while (!ghostManager.isGameOver()) {
            generateGhosts();
            }
    }

    private void generateGhosts() {
        while (!ghostManager.isGameOver()) {

            if (ghostManager.getGhostList().size() > 1) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (ghostManager.isGameOver()) {
                break;
            }

            for (int i = 0; i < numGhosts; i++) {
                ghostManager.createGhost(numShapes);
            }
            counter++;

            if (counter % GHOST_INTERVAL == 0) {

                if (numGhosts < MAX_GHOSTS) {
                    numGhosts++;
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
