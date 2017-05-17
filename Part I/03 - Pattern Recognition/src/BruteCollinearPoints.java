import java.util.Arrays;

public class BruteCollinearPoints
{
    private LineSegment[] res;
    private int size;
    
    public BruteCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new java.lang.NullPointerException();
        
        int N = points.length;
        
        // if any point in the array is null
        for (int i = 0; i < N; i++)
        {
            if (points[i] == null)
                throw new java.lang.NullPointerException();
        }
            
        // union duplicates
        Arrays.sort(points);
        
        // if the array contains a repeated point
        Point a = points[0];
        for (int i = 1; i < N; i++)
        {
            Point b = points[i];            
            if (a.compareTo(b) == 0)
                throw new java.lang.IllegalArgumentException();
            
            a = b;
        }
        
        res = new LineSegment[2];
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
                                if (res.length == size)
                                    res = Arrays.copyOf(res, 2 * res.length);
                                res[size++] = new LineSegment(line[0], line[3]);
                            }              
                        }
                    }
                }
            }
        }
    }
    
    public int numberOfSegments()
    {
        return size;
    }
    
    public LineSegment[] segments()
    {
        return res;
    }
}