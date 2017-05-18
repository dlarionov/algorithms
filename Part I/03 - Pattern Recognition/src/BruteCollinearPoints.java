import java.util.Arrays;
import java.util.ArrayList;
// import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints
{   
    private ArrayList<LineSegment> list = new ArrayList<LineSegment>();
    
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException();
        
        if (hasNulls(points))
            throw new java.lang.NullPointerException();
       
        if (hasDuplicates(points))
            throw new java.lang.IllegalArgumentException();
        
        if (points.length < 4)
            return;
        
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i+1; j < points.length; j++)
            {
                double ij = points[i].slopeTo(points[j]);
                for (int k = j+1; k < points.length; k++)
                {
                    double jk = points[j].slopeTo(points[k]);
                    if (equals(ij, jk))
                    {
                        for (int l = k+1; l < points.length; l++)
                        {
                            double kl = points[k].slopeTo(points[l]);
                            if (equals(jk, kl))
                            {
                                Point[] arr = new Point[] { points[i], points[j], points[k], points[l] };
                                Arrays.sort(arr);
                                list.add(new LineSegment(arr[0], arr[3]));
                            }
                        }
                    }
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