package draw.shapes;

public class Main {
    public static void main(String[] args) {
        ShapesView shapesView = new ShapesView();
        DrawShapesFrame drawShapesFrame = new DrawShapesFrame(shapesView);
        drawShapesFrame.setVisible(true);
    }
}
