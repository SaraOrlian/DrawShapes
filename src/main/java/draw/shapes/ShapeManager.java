package draw.shapes;

<<<<<<< HEAD
import java.text.Format;
import java.util.LinkedList;

//take care of adding and removing to queue
public class ShapeManager {

    private LinkedList<String> shapeList1 = new LinkedList<>();
    private final String horizontal = "\u2796";
    private final String vertical = "|";
    private final String ascii = "\u2796";

    public ShapeManager(){
        setupList(shapeList1);
    }

    private void setupList(LinkedList<String> shapeList1) {
        shapeList1.add(horizontal);
        shapeList1.add(horizontal);
        shapeList1.add(vertical);
        shapeList1.add(vertical);
        shapeList1.add(ascii);
        shapeList1.add(horizontal);
        shapeList1.add(horizontal);
        shapeList1.add(vertical);
    }

    public LinkedList<String> getShapeList1() {
        return shapeList1;
    }
=======
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//take care of adding and removing to queue
public class ShapeManager {
    List<Queue<Shape>> shapeQueues = new ArrayList<>();

>>>>>>> 12133e3598022045d07c6acc867e8f574348ba3c

}
