package draw.shapes;

import javax.swing.*;
import java.util.TimerTask;

public class PaintTask extends TimerTask {
    //capitalization is just for static final
    private final ShapesView VIEW;
    private final BombManager MANAGER;

    public PaintTask(ShapesView shapesView, BombManager manager) {
        this.VIEW = shapesView;
        this.MANAGER = manager;
    }

    @Override
    public void run() {
        VIEW.repaint();
        MANAGER.isGameOver();

    }


}
