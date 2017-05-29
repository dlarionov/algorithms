import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private Node root;
    private int size;
    
    public KdTree() {
        
    }
    
    private class Node {
        private Node left;
        private Node right;
        private Point2D point;
        private boolean odd;
        
        public Node(Point2D p) {
            point = p;
        }
        
        public int compareTo(Point2D p) {
            if (this.point.equals(p)) return 0;
            else if (this.odd) return Double.compare(this.point.x(), p.x());            
            else return Double.compare(this.point.y(), p.y());
        }
        
        public void addLeft(Point2D p) {
            left = new Node(p);
            left.odd = !odd;
        }
        
        public void addRight(Point2D p) {
            right = new Node(p);
            right.odd = !odd;
        }
    }
    
    public boolean isEmpty() { 
        return root == null; 
    }
    
    public int size() { 
        return size;
    }
    
    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        if (root == null) {
            root = new Node(p);
            size++;
            return;
        }
        
        Node x = root;
        while(x != null)
        {
            int cmp = x.compareTo(p);
            if (cmp < 0) {
                if (x.left == null) {
                    x.addLeft(p);
                    size++;
                    return;
                }
                x = x.left;
            }
            else if (cmp > 0) {
                if (x.right == null) {
                    x.addRight(p);
                    size++;
                    return;
                }
                x = x.right;
            }
            else
                return;
        }
    }
    
//    private Node put(Node x, Point2D p)
//    {
//        if (x == null) 
//            return new Node(p);
//        int cmp = x.compareTo(p);
//        if (cmp < 0)
//            x.left = put(x.left, p);
//        else if (cmp > 0)
//            x.right = put(x.right, p);
//        else if (cmp == 0)
//            x.point = p;
//        return x;
//    }
    
    public boolean contains(Point2D p) {
        if (root == null)
            return false;
        
        Node x = root;
        while(x != null)
        {
            int cmp = x.compareTo(p);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return true;
        }
        return false;
    }
    
    public void draw() {
//        for (Point2D p : set)
//            p.draw();
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.NullPointerException();
        
//        Stack<Point2D> arr = new Stack<Point2D>();
//        for (Point2D p : set)
//        {
//            if (rect.contains(p))
//                arr.push(p);            
//        }
//        return arr;
        return null;
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
//        if (set.isEmpty())
//            return null;
        
//        Point2D champion = null;
//        double distance = 2;
//        for (Point2D q : set) {
//            double d = p.distanceTo(q);
//            if (Double.compare(distance, d) > 0) {
//                champion = q;
//                distance = d;
//            }
//        }
//        return champion;
        return null;
    }        
    
    public static void main(String[] args) {
        
    }
}