package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        ShapesView shapesView = new ShapesView(analyzer);
        MouseListener listener = new MouseListener(shapesView);
        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(listener, shapesView);
        drawShapesFrame.setVisible(true);
    }
}
