package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        StrokeManager strokeManager = new StrokeManager(analyzer);
        StrokeListener listener = new StrokeListener(strokeManager);
        ShapesView shapesView = new ShapesView();
        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
    }
}
