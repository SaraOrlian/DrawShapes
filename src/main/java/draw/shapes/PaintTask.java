package draw.shapes;

import javax.swing.*;
import java.util.TimerTask;

public class PaintTask extends TimerTask {
    private final ShapesView VIEW;
    private final BombManager MANAGER;

    public PaintTask(ShapesView shapesView, BombManager manager) {
        this.VIEW = shapesView;
        this.MANAGER = manager;
    }

    @Override
    public void run() {
        VIEW.repaint();

        if(MANAGER.isGameOver()) {
            int response = JOptionPane.showConfirmDialog(VIEW, "Play Again?", "Game Over :(", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void restartGame() {
        MANAGER.clearBombs();
        BombThread thread = new BombThread(MANAGER, new PaintTask(this.VIEW, this.MANAGER));
        thread.start();
    }
}
