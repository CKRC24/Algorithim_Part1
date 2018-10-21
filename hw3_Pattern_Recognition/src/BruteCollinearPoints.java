import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private Point[] points;
    private int N;
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points){
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

    // function to calculate line segments
    private void calc_LineSegments(){
        for (int i = 0; i <= points.length - 4; i++){
            for (int j = i + 1; j <= points.length - 3; j++){
                for (int k = j + 1; k <= points.length - 2; k++){
                    for (int t = k + 1; t <= points.length - 1; t++){
                        double s1 = points[i].slopeTo(points[j]);
                        double s2 = points[j].slopeTo(points[k]);
                        double s3 = points[k].slopeTo(points[t]);
                        if (s1 == s2 && s2 == s3){
                            // get start point, end point
                            Point[] tmp = {points[i], points[j], points[k], points[t]};
                            Point min = points[i];
                            Point max = points[i];
                            for (int m = 1; m < 4; m++){
                                if (tmp[m].compareTo(min) < 0)
                                    min = tmp[m];
                                if (tmp[m].compareTo(max) > 0)
                                    max = tmp[m];
                            }

                            // store line
                            lines.add(new LineSegment(min, max));
                            N++;
                        }
                    }
                }
            }
        }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
