import java.util.Arrays;
import java.util.ArrayList;
// import edu.princeton.cs.algs4.StdOut;

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
        ArrayList<Point> band = new ArrayList<Point>();
        for (int i = 0; i < n-3; i++)
        {
            Point p = points[i];
            boolean hasSegment = false;
            Arrays.sort(copy, i, n, p.slopeOrder());
            assert copy[i] == p;
            
            double prev = p.slopeTo(copy[i+1]);            
            for (int j = i+2, offset = 0; j < n; j++)
            {
                double next = p.slopeTo(copy[j]);
                boolean eq = equals(prev, next);                
                if (eq)
                {
                    offset++;
                }
                
                if (offset >= 2 && (!eq || j == n-1))
                {
                    boolean hasParentSegment = false;
                    for (Point q : band)
                    {
                        if (equals(prev, p.slopeTo(q)))
                        {
                            hasParentSegment = true;
                            break;
                        }
                    }
                    
                    if (!hasParentSegment)
                    {                       
                        int hi = j;
                        if (eq)
                            hi = j+1;
                        int lo = hi-offset-1;
                        
                        Arrays.sort(copy, lo, hi);
                        Point a = copy[lo];
                        Point b = copy[hi-1];
                        
                        if (p.compareTo(a) < 0)
                            list.add(new LineSegment(p, b));
                        else if (p.compareTo(b) > 0)
                            list.add(new LineSegment(a, p));
                        else
                            list.add(new LineSegment(a, b));
                            
                        if (!hasSegment)
                            hasSegment = true;
                    }
                }
                
                if (!eq)
                {
                    prev = next;
                    offset = 0;
                }
            }
            
            if (hasSegment)
                band.add(p);
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