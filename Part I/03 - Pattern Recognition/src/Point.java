import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

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
        if (that.y == this.y)
        {
          if (that.x == this.x)
          {
             return Double.NEGATIVE_INFINITY; 
          }
          else
          {
              return Double.POSITIVE_INFINITY;
          }
        }
        
        return (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) 
    {
        if (this.y < that.y) return -1;
        else if (this.y < that.y) return 1;
        else if (this.x < that.x) return -1;
        else if (this.x > that.x) return 1;
        else return 0;
    }

    public Comparator<Point> slopeOrder() 
    {
        return new SlopeComparator();
    }
    
    private class SlopeComparator implements Comparator<Point>
    {
        public int compare(Point a, Point b)
        {
            double slopeA = slopeTo(a);
            double slopeB = slopeTo(b);
            if (slopeA < slopeB)  return -1;
            else if (slopeA > slopeB) return 1;
            else return 0;
        }
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) 
    {
        Point p = new Point(1, 1);
        StdOut.println(p.toString());
        StdOut.println(p.compareTo(new Point(2, 1)));
        StdOut.println(p.slopeTo(new Point(1, 1)));
    }
}
