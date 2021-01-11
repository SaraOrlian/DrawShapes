package draw.shapes;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();

        BombManager bombManager = new BombManager();
        ShapesView shapesView = new ShapesView(bombManager);
        StrokeListener listener = new StrokeListener(bombManager, shapesView, analyzer);

        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
        PaintTask paintTask = new PaintTask(shapesView, bombManager);

        ExplosionListener explosionListener = new ExplosionListener() {
            @Override
            public void onExplosion() {
                int response = JOptionPane.showConfirmDialog(shapesView, "Play Again?", "Game Over :(", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    bombManager.clearBombs();
                    BombThread thread = new BombThread(bombManager, new PaintTask(shapesView, bombManager));
                    thread.start();
                } else {
                    System.exit(0);
                }

            }
        };
        bombManager.setExplosionListener(explosionListener);


        BombThread bombThread = new BombThread(bombManager, paintTask);
        bombThread.start();
        while (true){
            if(bombManager.isGameOver()){
                bombThread.interrupt();
                bombManager.explodeBomb();
            }
        }
    }
}
