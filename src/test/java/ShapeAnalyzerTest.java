import draw.shapes.Point;
import draw.shapes.ShapeAnalyzer;
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
        stroke.add( new Point(110,385));
        stroke.add( new Point(112,381));
        stroke.add( new Point(113,379));
        stroke.add( new Point(116,377));
        stroke.add( new Point(118,373));
        stroke.add( new Point(122,368));
        stroke.add( new Point(127,362));
        stroke.add( new Point(133,354));
        stroke.add( new Point(139,345));
        stroke.add( new Point(146,335));
        stroke.add( new Point(152,325));
        stroke.add( new Point(158,315));
        stroke.add( new Point(163,305));
        stroke.add( new Point(169,297));
        stroke.add( new Point(174,288));
        stroke.add( new Point(175,287));
        stroke.add( new Point(179,284));
        stroke.add( new Point(180,284));
        stroke.add( new Point(184,286));
        stroke.add( new Point(185,289));
        stroke.add( new Point(188,293));
        stroke.add( new Point(189,295));
        stroke.add( new Point(190,300));
        stroke.add( new Point(193,308));
        stroke.add( new Point(198,317));
        stroke.add( new Point(202,327));
        stroke.add( new Point(206,338));
        stroke.add( new Point(211,350));
        stroke.add( new Point(215,361));
        stroke.add( new Point(220,371));
        stroke.add( new Point(223,380));
        stroke.add( new Point(226,385));
        stroke.add( new Point(226,386));
        stroke.add( new Point(227,388));
        stroke.add( new Point(227,389));
        stroke.add( new Point(228,390));

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
}
