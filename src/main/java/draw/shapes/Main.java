package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        ShapeFactory shapeFactory = new ShapeFactory();
        GhostFactory ghostFactory = new GhostFactory(shapeFactory);
        GhostManager ghostManager = new GhostManager(ghostFactory);
        StrokeManager strokeManager = new StrokeManager(analyzer, ghostManager);
        StrokeListener listener = new StrokeListener(strokeManager);
        ShapesView shapesView = new ShapesView(listener);
        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
    }
}
