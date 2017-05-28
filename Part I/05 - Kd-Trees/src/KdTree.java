import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private Node root;
    
    public KdTree() {
        
    }
    
    private class Node {
        private Node left;
        private Node right;
        private Point2D point;
        private boolean odd;
        private int cnt;
        
        public Node(Point2D p)
        {
            point = p;
            cnt = 1;
        }
        
        public int compareTo(Point2D p) {
            if (this.point.equals(p)) return 0;
            else if (this.odd) return Double.compare(this.point.x(), p.x());            
            else return Double.compare(this.point.y(), p.y());
        }
        
        public void addLeft(Point2D p) {
            Node n = new Node(p);
            left = n;
            cnt =+ n.cnt; // TODO
            n.odd = !odd;
        }
        
        public void addRight(Point2D p) {
            Node n = new Node(p);
            right = n;
            cnt =+ n.cnt; // TODO
            n.odd = !odd;
        }
    }
    
    public boolean isEmpty() { 
        return root == null; 
    }
    
    public int size() { 
        return root == null ? 0 : root.cnt;
    }
    
    public void insert(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        if (root == null) {
            root = new Node(p);
            return;
        }
        
        Node x = root;
        while(x != null)
        {
            int cmp = x.compareTo(p);
            if (cmp < 0) {
                if (x.left == null) {
                    x.addLeft(p);
                    return;
                }
                x = x.left;
            }
            else if (cmp > 0) {
                if (x.right == null) {
                    x.addRight(p);
                    return;
                }
                x = x.right;
            }
            else
                return;
        }
    }
    
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