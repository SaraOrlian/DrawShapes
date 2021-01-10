package draw.shapes;

import javax.swing.*;
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
        } else {
            int response = JOptionPane.showConfirmDialog(view, "Play Again?", "Game Over :(", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                //restartGame();
                System.exit(1);
            } else {
                System.exit(0);
            }
        }

    }
}
