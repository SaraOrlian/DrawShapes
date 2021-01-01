package draw.shapes;

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

}
