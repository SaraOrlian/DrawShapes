package draw.shapes;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        ShapeFactory shapeFactory = new ShapeFactory();
        BombFactory bombFactory = new BombFactory(shapeFactory);
        BombManager bombManager = new BombManager(bombFactory);
        ShapesView shapesView = new ShapesView(bombManager);
        StrokeListener listener = new StrokeListener(bombManager, shapesView, analyzer);

        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
        int instruction = JOptionPane.showConfirmDialog(shapesView, "Draw the respective shapes of the bombs to diffuse the bombs and protect the neighboring towns", "Instructions", JOptionPane.OK_CANCEL_OPTION);
        if (instruction == JOptionPane.OK_OPTION) {

        ExplosionListener explosionListener = new ExplosionListener() {
            @Override
            public void onExplosion() {
                int response = JOptionPane.showConfirmDialog(shapesView, "Play Again?", "Game Over :(", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    bombManager.clearBombs();
                    BombThread thread = new BombThread(bombManager, shapesView);
                    thread.start();
                } else {
                    System.exit(0);
                }

            }
        };
        bombManager.setExplosionListener(explosionListener);

        BombThread thread = new BombThread(bombManager, shapesView);
        thread.start();
    }
    else {
        System.exit(0);
    }
}}
