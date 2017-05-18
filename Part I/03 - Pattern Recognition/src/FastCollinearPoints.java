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
                if (prev == next)
                {
                    k++;
                }
                else
                {
                    if (k >= 3)
                    {
                        Point[] line = new Point[k+1]; // TODO use inplace sort
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
    
    private static boolean hasNulls(Point[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == null)
                return true;
        }
        return false;
    }
    
    private static boolean hasDuplicates(Point[] arr)
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
}