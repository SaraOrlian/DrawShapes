import draw.shapes.Point;
import draw.shapes.ShapeAnalyzer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeAnalyzerTest {

    @Test
    public void isV() {
//given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        List<Point> stroke = new ArrayList<Point>();
        int slope = 1;
        //when
        for (int i = 0; i < 40; i++) {
            stroke.add(new Point(i, i + 40));
        }
//        stroke.add(new Point(10,50));
//        stroke.add(new Point(20,60));
//        stroke.add(new Point(30,70));
        stroke.add(new Point(40, 80)); //vertex

        int num = 79;
        for (int i = 41; i < 70; i++) {
            stroke.add(new Point(i, num));
            num--;
        }
//        stroke.add(new Point(50, 70));
//        stroke.add(new Point(60, 60));
//        stroke.add(new Point(70, 50));

        //then
        assertTrue(analyzer.isVee(stroke));

    }

    @Test
    public void isVertical() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        List<Point> stroke = new ArrayList<Point>();

        //when
        for (int i = 0; i < 100; i++) {
            Point temp = new Point(75, i);
            stroke.add(i, temp);
        }
        //then
        assertTrue(analyzer.isVertical(stroke));
    }

    @Test
    public void isHorizontal() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        List<Point> stroke = new ArrayList<Point>();

        //when
        for (int i = 0; i < 100; i++) {
            Point temp = new Point(i, 75);
            stroke.add(i, temp);
        }
        //then
        assertTrue(analyzer.isHorizontal(stroke));
    }

    @Test
    public void isCarat() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        List<Point> stroke = new ArrayList<Point>();


        int y = 500;
        //when
        for (int i = 100; i < 400; i++) {
            stroke.add(new Point(i, y));
            y--;
        }
        stroke.add(new Point(400, 200));

        for (int i = 401; i < 700; i++) {
            stroke.add(new Point(i, y));
            y++;
        }

        //then
        assertTrue(analyzer.isCarat(stroke));
    }
}
