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
        
        if (points.length < 4)
            return;
        
        Point[] copy = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++)
        {
            Point p = points[i];
            Arrays.sort(copy, p.slopeOrder());
            
            double prev = p.slopeTo(copy[1]);
            for (int j = 2, k = 1; j < copy.length; j++)
            {
                double next = p.slopeTo(copy[j]);
                if (equals(prev, next))
                {
                    k++;
                }
                else
                {
                    if (k >= 3)
                    {
                        int x = j-k-1;
                        if (x > 0)
                        {
                            Point t = copy[x];
                            copy[x] = copy[0];
                            copy[0] = t;
                        }
                        Arrays.sort(copy, x, j);
                        LineSegment s = new LineSegment(copy[x], copy[j-1]);
                        // addSegment(s);
                        list.add(s);
                    }
                    
                    k = 1;
                    prev = next;
                }
            }
        }
    }
    
    private void addSegment(LineSegment segment)
    {
        if (list.isEmpty())
        {
            list.add(segment);
        }
        else
        {
            String path = segment.toString();
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).toString().equals(path))
                    return;
            }
            list.add(segment);
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