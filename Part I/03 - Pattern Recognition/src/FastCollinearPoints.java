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
                    // TODO !sinle points
                    if (!duplicate(copy, p, i, prev))
                    {
                        Point[] line = Arrays.copyOfRange(copy, j-offset-1, j+1);
                        line[offset+1] = p;
                        Arrays.sort(line);                    
                        list.add(new LineSegment(line[0], line[offset+1]));
                    }
                }
                
                if (!eq)
                {
                    prev = next;
                    offset = 0;
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
    
    private static boolean duplicate(Point[] arr, Point p, int length, double alfa)
    {
        for (int i = 0; i < length; i++)
        {
            if (equals(alfa, p.slopeTo(arr[i])))
            {
                return true;
            }
        }
        return false;
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