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
       
        if (hasDuplicates(Arrays.copyOf(points, points.length)))
            throw new java.lang.IllegalArgumentException();
        
        if (points.length < 4)
            return;
        
        Point[] line = new Point[4];   
        for (int i = 0; i < points.length; i++)
        {
            line[0] = points[i];        
            for (int j = i+1; j < points.length; j++)
            {
                line[1] = points[j];
                double pq = line[1].slopeTo(line[0]);
                for (int k = j+1; k < points.length; k++)
                {
                    line[2] = points[k];
                    double qr = line[1].slopeTo(line[2]);
                    if (pq == qr)
                    {
                        for (int l = k+1; l < points.length; l++)
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
    
    private static boolean hasNulls(Object[] arr)
    {
        for(Object obj : arr)
        {
            if (obj == null)
                return true;
        }
        return false;
    }
    
    private static boolean hasDuplicates(Point[] arr)
    {
        if (arr.length < 2)
            return false;
        
        Arrays.sort(arr);
        
        Point a = arr[0];
        for (int i = 1; i < arr.length; i++)
        {
            Point b = arr[i];
            if (a.compareTo(b) == 0)
                return true;           
            a = b;
        }
        return false;
    }
}