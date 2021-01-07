package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Ghost {

    private int xVal;
    private int yVal;
    static final Random random = new Random();
    Queue<Shape> shapeQueue;

    public Ghost() {
        //xVal = random.nextInt(400);
        //yVal = random.nextInt(400);
        shapeQueue = new LinkedList<>();
    }

    public Queue<Shape> getShapeQueue() {
        return shapeQueue;
    }

    public int getxVal() {
        return xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }
    
    public void setyVal(int yVal){
        this.yVal =yVal;
    }
}
