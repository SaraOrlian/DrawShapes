package draw.shapes;

import java.util.TimerTask;

public class PaintTask extends TimerTask {
    private final ShapesView VIEW;

    public PaintTask(ShapesView shapesView, BombManager bombManager) {
        this.VIEW = shapesView;
    }

    @Override
    public void run() {
        VIEW.repaint();

    }
}
