package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        GhostManager ghostManager = new GhostManager(analyzer);
        StrokeListener listener = new StrokeListener(ghostManager);
        ShapesView shapesView = new ShapesView(listener, ghostManager);
        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
    }
}
