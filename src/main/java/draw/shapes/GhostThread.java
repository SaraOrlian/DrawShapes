package draw.shapes;

public class GhostThread extends Thread{

    private static final int DELAY = 3000;
    private int counter;
    private final GhostManager ghostManager;
    private final ShapesView shapesView;

    public GhostThread(GhostManager ghostManager, ShapesView shapesView) {
        this.ghostManager = ghostManager;
        this.shapesView = shapesView;
    }

    public void run() {
        while (counter < 100) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ghostManager.createGhost(2);
            counter++;
            shapesView.repaint();
        }
    }
}
