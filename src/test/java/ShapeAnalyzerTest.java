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
        int peakX = 4;
        int num = 3;
        //when
        num = 5;
        for (int i = 0; i < 3; i++) {
            Point temp = new Point(peakX + i, i);
            stroke.add(i, temp);
            num++;
        }

        stroke.add(new Point(peakX, 2));

        for (int i = 0; i < peakX-1; i++) {
            Point temp = new Point(1, num);
            stroke.add(i, temp);
            num--;
        }
        //then
        assertTrue(analyzer.isV(stroke));

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
        int peakX = 4;
        int num = 5;
        //when
        for (int i = 0; i < peakX-1; i++) {
            Point temp = new Point(1, num);
            stroke.add(i, temp);
            num--;
        }
        stroke.add(new Point(peakX, 2));

        num = 3;
        for (int i = 1; i < 4; i++) {
            Point temp = new Point(peakX + i, i);
            stroke.add(i, temp);
            num++;
        }

        //then
        assertTrue(analyzer.isCarat(stroke));
    }
}
