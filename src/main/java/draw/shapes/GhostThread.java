package draw.shapes;

public class GhostThread extends Thread{

    private int delay = 5000;
    private int counter;
    private int numShapes = 2;
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
            ghostManager.createGhost(numShapes);
            counter++;
            if(counter%5 == 0) {
                numShapes++;
                delay -=100;
            }
            shapesView.repaint();
        }
    }
}
