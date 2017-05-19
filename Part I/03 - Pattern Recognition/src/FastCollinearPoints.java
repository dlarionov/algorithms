import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{   
    private ArrayList<LineSegment> list = new ArrayList<LineSegment>();
    
    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException();
        
        if (hasNulls(points))
            throw new java.lang.NullPointerException();
        
        if (hasDuplicates(points))
            throw new java.lang.IllegalArgumentException();
        
        int n = points.length;
        if (n < 4)
            return;
        
        Point[] copy = Arrays.copyOf(points, n);
        for (int i = 0; i < n-3; i++)
        {
            Point p = points[i];
            Arrays.sort(copy, i, n, p.slopeOrder());
            assert copy[i] == p;     
            
            double prev = p.slopeTo(copy[i+1]);
            for (int j = i+2, k = 1; j < n; j++)
            {
                double next = p.slopeTo(copy[j]);
                if (equals(prev, next))
                {
                    k++;
                    if (j == n-1 && k >= 3)
                    {
                        Point[] line = Arrays.copyOfRange(copy, j-k, j+1);
                        line[k] = p;
                        Arrays.sort(line);
                        LineSegment s = new LineSegment(line[0], line[k]);                        
                        list.add(s);
                    }
                }
                else
                {
                    if (k >= 3)
                    {
                        Point[] line = Arrays.copyOfRange(copy, j-k, j+1);
                        line[k] = p;
                        Arrays.sort(line);
                        LineSegment s = new LineSegment(line[0], line[k]);                        
                        list.add(s);
                    }
                    
                    k = 1;
                    prev = next;
                }
            }
        }
    }
    
    public int numberOfSegments()
    {
        return list.size();
    }
    
    public LineSegment[] segments()
    {
        return list.toArray(new LineSegment[list.size()]);
    }
    
    private static boolean equals(final double a, final double b)
    {
        if (a == b) 
            return true;

        return Math.abs(a - b) < 0.0000001d;
    }
    
    private static boolean hasNulls(Object[] arr)
    {
        for (Object obj : arr)
        {
            if (obj == null)
                return true;
        }
        return false;
    }
    
    private static boolean hasDuplicates(Point[] points)
    {
        if (points.length < 2)
            return false;
        
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);
        
        Point a = copy[0];
        for (int i = 1; i < copy.length; i++)
        {
            Point b = copy[i];
            if (a.compareTo(b) == 0)
                return true;
            a = b;
        }
        return false;
    }
}