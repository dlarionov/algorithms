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
        
        Point[] copy = Arrays.copyOf(points, points.length);
        for (int i = 0; i < N; i++)
        {
            Point p = points[i];
            Arrays.sort(copy, p.slopeOrder());
            
            // copy[0] is -Infiniy
            double prev = p.slopeTo(copy[1]);
            for (int j = 2, k = 1; j < N; j++)
            {
                double next = p.slopeTo(copy[j]);
                if (prev == next)
                {
                    k++;
                }
                else
                {
                    if (k >= 3)
                    {
                        Point[] line = new Point[k+1];
                        line[0] = p;
                        while (k > 0)
                        {
                            line[k] = copy[j-k];
                            k--;
                        }
                        Arrays.sort(line);
                        list.add(new LineSegment(line[0], line[line.length-1]));
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
}