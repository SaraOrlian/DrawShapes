package draw.shapes;

import javax.swing.*;
import java.util.TimerTask;

public class PaintTask extends TimerTask {
    //capitalization is just for static final
    private final ShapesView VIEW;


    public PaintTask(ShapesView shapesView) {
        this.VIEW = shapesView;
    }

    @Override
    public void run() {
        VIEW.repaint();
    }


}
