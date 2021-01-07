package draw.shapes;

public class GhostThread extends Thread{

    private int delay = 50;
    private final int POPULATION_DELAY= 500;
    private int counter;
    private final int MIN_DELAY = 2000;
    private final int MAX_GHOSTS = 10;
    private final int MAX_SHAPES = 6;
    private int numShapes = 2;
    private int numGhosts = 1;
    private final GhostManager ghostManager;
    private final ShapesView shapesView;

    public GhostThread(GhostManager ghostManager, ShapesView shapesView) {
        this.ghostManager = ghostManager;
        this.shapesView = shapesView;
    }

    public void run() {
        while (!ghostManager.isGameOver()) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < numGhosts; i++) {
                ghostManager.createGhost(numShapes);
                try {
                    Thread.sleep(POPULATION_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                shapesView.repaint();
            }
            counter++;
            if(counter%10 == 0) {
                if (numGhosts < MAX_GHOSTS){
                    numGhosts++;
                }
                if(delay > MIN_DELAY) {
                    delay -=100;
                }

            }
            if(counter%20 == 0) {
                if (numShapes < MAX_SHAPES) {
                    numShapes++;
                }
                if(delay > MIN_DELAY) {
                    delay -=50;
                }
            }
        }
    }
}
