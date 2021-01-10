package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        BombManager bombManager = new BombManager();
        ShapesView shapesView = new ShapesView(bombManager);
        StrokeListener listener = new StrokeListener(bombManager, shapesView, analyzer);

        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
        PaintTask paintTask = new PaintTask(shapesView, bombManager);
        BombThread thread = new BombThread(bombManager, paintTask);
        thread.start();
    }
}
