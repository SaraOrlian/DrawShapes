package draw.shapes;

import java.util.TimerTask;

public class PaintTask extends TimerTask {
    private final ShapesView view;
    private final GhostManager manager;

    public PaintTask(ShapesView shapesView, GhostManager ghostManager) {
        this.view = shapesView;
        this.manager = ghostManager;
    }

    @Override
    public void run() {
        if (!manager.isGameOver()){
            view.repaint();
        }

    }
}
