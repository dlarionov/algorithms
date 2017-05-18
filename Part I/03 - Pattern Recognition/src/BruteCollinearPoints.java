import java.util.Arrays;
import java.util.ArrayList;
// import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints
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
    
    public BruteCollinearPoints(Point[] points)
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
        
        Point[] line = new Point[4];   
        for (int i = 0; i < N; i++)
        {
            line[0] = points[i];        
            for (int j = i+1; j < N; j++)
            {
                line[1] = points[j];
                double pq = line[1].slopeTo(line[0]);
                for (int k = j+1; k < N; k++)
                {
                    line[2] = points[k];
                    double qr = line[1].slopeTo(line[2]);
                    if (pq == qr)
                    {
                        for (int l = k+1; l < N; l++)
                        {
                            line[3] = points[l];
                            double rs = line[2].slopeTo(line[3]);
                            if (qr == rs)
                            {
                                Arrays.sort(line);                          
                                list.add(new LineSegment(line[0], line[3]));
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
}