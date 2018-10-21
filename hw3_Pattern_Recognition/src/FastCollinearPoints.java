import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int N;
    private Point[] points;
    private ArrayList<LineSegment> lines;

    public FastCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException();
        this.points = points;
        checkItems(); // check if there are duplicate items, null items

        // initialize
        N = 0;
        lines = new ArrayList<>();

        // calculate LineSegments
        calc_LineSegments();
    }

    public int numberOfSegments(){
        return N;
    }

    public LineSegment[] segments(){
        return lines.toArray(new LineSegment[lines.size()]);
    }

    // function to check if there are repeated items
    private void checkItems(){
        for (int i = 0; i < points.length; i++){
            if (points[i] == null)
                throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++){
                if (points[i].compareTo(points[j]) == 0 || points[j] == null)
                    throw new IllegalArgumentException();
            }
        }
    }

    // function to calculate slope and sort
    private void calc_LineSegments(){
        // stack to store same slope points
        ArrayList<Double> slopes = new ArrayList<>();
        ArrayList<Point> tmp = new ArrayList<>();
        Point[] p_clone = points.clone();
        Double prev_slope = null;

        // iterate all points
        for (int i = 0; i < points.length; i++) {
            // set current point as origin
            Point origin = points[i];

            // sort points with slope
            Comparator<Point> c = origin.slopeOrder();
            Arrays.sort(p_clone, c);

            // store slopes in a array
            for (int j = 0; j < p_clone.length; j++) {
                if (origin.equals(p_clone[j]))
                    continue;
                slopes.add(origin.slopeTo(p_clone[j]));
            }

            // get line segments
            for (int k = 0; k < slopes.size(); k++){
                if (!slopes.get(k).equals(prev_slope) && tmp.size() != 0){
                    if (tmp.size() >= 3){
                        Point min = origin;
                        Point max = origin;
                        for (Point p: tmp){
                            if (p.compareTo(min) < 0)
                                min = p;
                            if (p.compareTo(max) > 0)
                                max = p;
                        }
                        // store line
                        lines.add(new LineSegment(min, max));
                        N++;
                    }

                    // clear the tmp array
                    tmp.clear();
                }
                // add current point (except origin) to stack
                tmp.add(p_clone[k+1]);
                prev_slope = slopes.get(k);

            }
            // release after iterate all points with this origin
            slopes.clear();
            tmp.clear();
            prev_slope = null;
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
