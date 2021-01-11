package draw.shapes;

import java.util.TimerTask;

public class PaintTask extends TimerTask {

    private final ShapesView view;

    public PaintTask(ShapesView shapesView, BombManager manager) {
        this.view = shapesView;

    }

    @Override
    public void run() {
        view.repaint();
    }


}
