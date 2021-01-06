package draw.shapes;

public class GhostFactory {

    ShapeFactory shapeFactory = new ShapeFactory();

    public Ghost newInstance(int numShapes) {
        Ghost ghost = new Ghost();
        for(int i = 0; i < numShapes; i++) {
            ghost.shapeQueue.add(shapeFactory.newInstance());
        }
        return ghost;
    }
}
