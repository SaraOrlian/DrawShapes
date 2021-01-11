package draw.shapes;

import java.util.TimerTask;

public class GameOverTask extends TimerTask {
    private final BombManager MANAGER;

    public GameOverTask(BombManager manager) {
        this.MANAGER = manager;
    }

    @Override
    public void run() {

        if (MANAGER.isGameOver()) {
            MANAGER.explodeBomb();
        }

    }

}
