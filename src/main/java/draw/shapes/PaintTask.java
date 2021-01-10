package draw.shapes;

import java.util.TimerTask;

public class PaintTask extends TimerTask {
    private final ShapesView view;
    private final BombManager manager;

    public PaintTask(ShapesView shapesView, BombManager bombManager) {
        this.view = shapesView;
        this.manager = bombManager;
    }

    @Override
    public void run() {
        view.repaint();

    }
}
