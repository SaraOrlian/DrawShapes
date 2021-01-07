import draw.shapes.Point;
import draw.shapes.ShapeAnalyzer;
import draw.shapes.ShapeReducer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShapeAnalyzerTest {

//
//    @Test
//    public void isDownLine() {
//        //given
//        ShapeAnalyzer analyzer = new ShapeAnalyzer();
//        List<Point> stroke = new ArrayList<Point>();
//        Point origin = new Point(10, 10);
//        //when
//        stroke.add(new Point(5, 5));
//        stroke.add(new Point(6, 6));
//        stroke.add(new Point(7, 7));
//        stroke.add(new Point(8, 8));
//        stroke.add(new Point(9, 9));
//        stroke.add(new Point(10, 10));
//        //then
//        assertFalse(analyzer.isUpLine(stroke, origin, stroke.size()));
//        assertTrue(analyzer.isDownLine(stroke, origin, stroke.size()));
//    }
//
//    @Test
//    public void isUpLine() {
//        //given
//        ShapeAnalyzer analyzer = new ShapeAnalyzer();
//        List<Point> stroke = new ArrayList<Point>();
//        Point origin = new Point(5, 5);
//        //when
//        stroke.add(new Point(10, 10));
//        stroke.add(new Point(9, 9));
//        stroke.add(new Point(8, 8));
//        stroke.add(new Point(7, 7));
//        stroke.add(new Point(6, 6));
//        stroke.add(new Point(5, 5));
//
//        //then
//        assertFalse(analyzer.isDownLine(stroke,origin,stroke.size()));
//        assertTrue(analyzer.isUpLine(stroke, origin, stroke.size()));
//    }

    @Test
    public void isVee() {
//given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        List<Point> stroke = new ArrayList<Point>();

        //when
        stroke.add(new Point(103, 130));
        stroke.add(new Point(103, 131));
        stroke.add(new Point(104, 133));
        stroke.add(new Point(105, 137));
        stroke.add(new Point(106, 143));
        stroke.add(new Point(108, 151));
        stroke.add(new Point(111, 161));
        stroke.add(new Point(114, 173));
        stroke.add(new Point(116, 185));
        stroke.add(new Point(119, 197));
        stroke.add(new Point(122, 209));
        stroke.add(new Point(125, 223));
        stroke.add(new Point(129, 235));
        stroke.add(new Point(132, 246));
        stroke.add(new Point(134, 255));
        stroke.add(new Point(136, 262));
        stroke.add(new Point(136, 264));
        stroke.add(new Point(137, 265));
        stroke.add(new Point(138, 265));
        stroke.add(new Point(140, 265));
        stroke.add(new Point(144, 263));
        stroke.add(new Point(148, 259));
        stroke.add(new Point(149, 256));
        stroke.add(new Point(152, 252));
        stroke.add(new Point(153, 250));
        stroke.add(new Point(155, 245));
        stroke.add(new Point(159, 239));
        stroke.add(new Point(165, 232));
        stroke.add(new Point(170, 225));
        stroke.add(new Point(175, 217));
        stroke.add(new Point(180, 209));
        stroke.add(new Point(186, 200));
        stroke.add(new Point(191, 193));
        stroke.add(new Point(195, 184));
        stroke.add(new Point(198, 177));
        stroke.add(new Point(201, 171));
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

    @Test
    public void calcPosSlope() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        Point point1 = new Point(1, 2);
        Point point2 = new Point(2, 1);
        int expectedSlope = 1;
        //when
        int slopeResult = (int) analyzer.calcSlope(point1, point2);
        //then
        assertEquals(expectedSlope, slopeResult);
    }

    @Test
    public void calcNegSlope() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 2);
        int expectedSlope = -1;
        //when
        int slopeResult = (int) analyzer.calcSlope(point1, point2);
        //then
        assertEquals(expectedSlope, slopeResult);
    }

    @Test
    public void calcUndefSlope() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 1);
        double expectedSlope = Double.NEGATIVE_INFINITY;
        //when
        double slopeResult = analyzer.calcSlope(point1, point2);
        //then
        assertEquals((int) expectedSlope, (int) slopeResult);
    }

    @Test
    public void calcNaNSlope() {
        //given
        ShapeAnalyzer analyzer = new ShapeAnalyzer();
        Point point1 = new Point(1, 2);
        Point point2 = new Point(1, 2);
        double expectedSlope = Double.NaN;
        //when
        double slopeResult = analyzer.calcSlope(point1, point2);
        //then
        assertEquals((int) expectedSlope, (int) slopeResult);
    }

    @Test
    public void smooth() {
        //given
        ShapeReducer reducer = new ShapeReducer();

        List<Point> stroke = new ArrayList<>();
        stroke.add(new Point(103, 130));
        stroke.add(new Point(103, 131));
        stroke.add(new Point(104, 133));
        stroke.add(new Point(105, 137));
        stroke.add(new Point(106, 143));
        stroke.add(new Point(108, 151));
        stroke.add(new Point(111, 161));
        stroke.add(new Point(114, 173));
        stroke.add(new Point(116, 185));
        stroke.add(new Point(119, 197));
        stroke.add(new Point(122, 209));
        stroke.add(new Point(125, 223));
        stroke.add(new Point(129, 235));
        stroke.add(new Point(132, 246));
        stroke.add(new Point(134, 255));
        stroke.add(new Point(136, 262));
        stroke.add(new Point(136, 264));
        stroke.add(new Point(137, 265));
        stroke.add(new Point(138, 265));
        stroke.add(new Point(140, 265));
        stroke.add(new Point(144, 263));
        stroke.add(new Point(148, 259));
        stroke.add(new Point(149, 256));
        stroke.add(new Point(152, 252));
        stroke.add(new Point(153, 250));
        stroke.add(new Point(155, 245));
        stroke.add(new Point(159, 239));
        stroke.add(new Point(165, 232));
        stroke.add(new Point(170, 225));
        stroke.add(new Point(175, 217));
        stroke.add(new Point(180, 209));
        stroke.add(new Point(186, 200));
        stroke.add(new Point(191, 193));
        stroke.add(new Point(195, 184));
        stroke.add(new Point(198, 177));
        stroke.add(new Point(201, 171));

        //when
        int oldSize = stroke.size();
        List<Point> newList = reducer.smooth(stroke);


        int newSize = newList.size();
        System.out.println(oldSize);
        System.out.println(newSize);

        for (Point point : newList) {
            System.out.println(point);
        }

        boolean shrunk = newSize < oldSize;

        //then
        assertTrue(shrunk);

    }

    @Test
    public void reallySmooth() {
        //given
        ShapeReducer reducer = new ShapeReducer();

        List<Point> stroke = new ArrayList<>();
        stroke.add(new Point(103, 130));
        stroke.add(new Point(103, 131));
        stroke.add(new Point(104, 133));
        stroke.add(new Point(105, 137));
        stroke.add(new Point(106, 143));
        stroke.add(new Point(108, 151));
        stroke.add(new Point(111, 161));
        stroke.add(new Point(114, 173));
        stroke.add(new Point(116, 185));
        stroke.add(new Point(119, 197));
        stroke.add(new Point(122, 209));
        stroke.add(new Point(125, 223));
        stroke.add(new Point(129, 235));
        stroke.add(new Point(132, 246));
        stroke.add(new Point(134, 255));
        stroke.add(new Point(136, 262));
        stroke.add(new Point(136, 264));
        stroke.add(new Point(137, 265));
        stroke.add(new Point(138, 265));
        stroke.add(new Point(140, 265));
        stroke.add(new Point(144, 263));
        stroke.add(new Point(148, 259));
        stroke.add(new Point(149, 256));
        stroke.add(new Point(152, 252));
        stroke.add(new Point(153, 250));
        stroke.add(new Point(155, 245));
        stroke.add(new Point(159, 239));
        stroke.add(new Point(165, 232));
        stroke.add(new Point(170, 225));
        stroke.add(new Point(175, 217));
        stroke.add(new Point(180, 209));
        stroke.add(new Point(186, 200));
        stroke.add(new Point(191, 193));
        stroke.add(new Point(195, 184));
        stroke.add(new Point(198, 177));
        stroke.add(new Point(201, 171));

        //when


        int oldSize = stroke.size();
        System.out.println(oldSize);

        List<Point> newList = reducer.smooth(stroke);

        while (newList.size() > 3) {
            newList = reducer.smooth(newList);
            System.out.println(newList.size());
        }


        int newSize = newList.size();

        System.out.println(newSize);

        for (Point point : newList) {
            System.out.println(point);
        }

        boolean shrunk = newSize < oldSize;

        //then
        assertTrue(shrunk);


    }
}
