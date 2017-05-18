import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> 
{
    private final int x;
    private final int y;
    
    public Point(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw() 
    {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that) 
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    public double slopeTo(Point that) 
    {
        double r;
        if (that.x == this.x && that.y == this.y) r = Double.NEGATIVE_INFINITY;
        else if (that.x == this.x) r = Double.POSITIVE_INFINITY;
        else if (that.y == this.y) r = 0;
        else r = (double) (that.y - this.y) / (that.x - this.x);        
        return r;
    }
    
    public int compareTo(Point that) 
    {
        int r;        
        if (this.y < that.y) r = -1;
        else if (this.y > that.y) r = 1;
        else if (this.x < that.x) r = -1;
        else if (this.x > that.x) r = 1;
        else r = 0;        
        return r;
    }
    
    public Comparator<Point> slopeOrder() 
    {
        return new SlopeComparator();
    }
    
    private class SlopeComparator implements Comparator<Point>
    {
        public int compare(Point a, Point b)
        {
            return Double.compare(slopeTo(a), slopeTo(b));
        }
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    public static void main(String[] args) 
    {
        
    }
}
