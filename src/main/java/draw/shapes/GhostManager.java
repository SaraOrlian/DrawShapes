package draw.shapes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//take care of adding and removing to queue
public class GhostManager {

    GhostFactory ghostFactory;
    List<Queue<Shape>> ghostList = new ArrayList<>();

    public GhostManager(GhostFactory ghostFactory) {
        this.ghostFactory = ghostFactory;
    }

    public void createGhost(int numShapes) {
        ghostList.add(ghostFactory.newInstance(numShapes));
    }

    public List<Queue<Shape>> getGhostList() {
        return ghostList;
    }

}
