import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{   
    private ArrayList<LineSegment> list = new ArrayList<LineSegment>();    
    
    // if any point in the array is null
    private static boolean hasNulls(Point[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == null)
                return true;
        }
        return false;
    }
    
    // if the array contains a repeated point
    private static boolean hasDuplicates(Point[] arr) // TODO it should be O(n)
    {
        Arrays.sort(arr);
        
        Point a = arr[0];
        for (int i = 1; i < arr.length; i++)
        {
            Point b = arr[i];            
            if (a == b)
                return true;           
            a = b;
        }
        return false;
    }
    
    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException();
        
        if (hasNulls(points))
            throw new java.lang.NullPointerException();
       
        if (hasDuplicates(points))
            throw new java.lang.IllegalArgumentException();
        
        int N = points.length;
        if (N < 4)
            return;
        
        for (int i = 0; i < N-3; i++)
        {
            Point p = points[i];
            double[] slaps = new double[N-1-i];
            for (int j = i+1; j < N; j++)
            {
                Point q = points[j];
                slaps[j-1-i] = p.slapTo(q);
            }
            Arrays.sort(slaps);
            slaps = new double[N-i];
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
}