import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
    private SET<Point2D> set;
    
    public PointSET() {
        set = new SET<Point2D>();
    }
    
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    public int size() {
        return set.size();
    }
    
    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        if (!set.contains(p))
            set.add(p);
    }
    
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    
    public void draw() {
        for (Point2D p : set)
            p.draw();
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.NullPointerException();
        
        Stack<Point2D> arr = new Stack<Point2D>();
        for (Point2D p : set)
        {
            if (rect.contains(p))
                arr.push(p);            
        }
        return arr;
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        if (set.isEmpty())
            return null;
        
        Point2D champion = null;
        double distance = Double.POSITIVE_INFINITY;
        for (Point2D q : set) {
            double d = p.distanceTo(q);
            if (Double.compare(distance, d) > 0) {
                champion = q;
                distance = d;
            }
        }
        return champion;
    }        
    
    public static void main(String[] args) {
        
    }
}