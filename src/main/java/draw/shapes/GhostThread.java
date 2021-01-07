package draw.shapes;

public class GhostThread extends Thread{

    private int delay = 5000;
    private int counter;
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
            }
            counter++;
            if(counter%20 == 0) {
                numGhosts++;
                delay -=100;
            }
            if(counter%10 == 0) {
                numShapes++;
                delay -=50;
            }
            shapesView.repaint();
        }
    }
}
