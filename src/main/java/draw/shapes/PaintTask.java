package draw.shapes;

import javax.swing.*;
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

        if(manager.isGameOver()) {
            int response = JOptionPane.showConfirmDialog(view, "Play Again?", "Game Over :(", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                //restartGame
                manager.clearBombs();
                BombThread thread = new BombThread(manager, new PaintTask(this.view, this.manager));
                thread.start();
            } else {
                System.exit(0);
            }
        }

    }
}
