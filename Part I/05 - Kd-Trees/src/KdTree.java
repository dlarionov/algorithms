import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdOut;

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
        
        public int compareToPoint(Point2D p) {
            if (this.point.equals(p))
                return 0;
            else if (this.odd)
                return Double.compare(this.point.y(), p.y());
            else
                return Double.compare(this.point.x(), p.x());
        }
        
        public int compareToRect(RectHV rect) {
            if (this.odd) {
                if (Double.compare(this.point.y(), rect.ymax()) > 0)
                    return 1;
                else if (Double.compare(this.point.y(), rect.ymin()) < 0)
                    return -1;
                else
                    return 0;
            }
            else {
                if (Double.compare(this.point.x(), rect.xmax()) > 0)
                    return 1;
                else if (Double.compare(this.point.x(), rect.xmin()) < 0)
                    return -1;
                else
                    return 0;
            }
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
            int cmp = x.compareToPoint(p);
            if (cmp < 0) {
                if (x.left == null) {
                    x.left = new Node(p);
                    x.left.odd = !x.odd;
                    size++;
                    return;
                }
                x = x.left;
            }
            else if (cmp > 0) {
                if (x.right == null) {
                    x.right = new Node(p);
                    x.right.odd = !x.odd;
                    size++;
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
            int cmp = x.compareToPoint(p);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return true;
        }
        return false;
    }
    
    public void draw() {
        draw(root);
    }
    
    private static void draw(Node node) {
        if (node == null)
            return;
        node.point.draw();
        draw(node.left);
        draw(node.right);
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.NullPointerException();
        
        Stack<Point2D> arr = new Stack<Point2D>();        
        range(rect, root, arr);        
        return arr;
    }
    
    private static void range(RectHV rect, Node node, Stack<Point2D> res) {
        if (node == null)
            return;
        
        if (rect.contains(node.point))
            res.push(node.point);
        
        int cmp = node.compareToRect(rect);
        if (cmp < 0)
            range(rect, node.left, res);
        else if (cmp > 0)
            range(rect, node.right, res);
        else {
            range(rect, node.left, res);
            range(rect, node.right, res);
        }
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        // TODO
        
        return null;
    }        
    
    public static void main(String[] args) {
        StdDraw.setPenRadius(.001);
        StdDraw.setPenColor(StdDraw.GRAY);
        RectHV r = new RectHV(0.2, 0.2, 0.8, 0.8);
        r.draw();
        
        StdDraw.setPenRadius(.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        KdTree t = new KdTree();
        t.insert(new Point2D(0.9, 0.8));
        t.insert(new Point2D(0.8, 0.2));
        t.insert(new Point2D(0.206107, 0.904508));
        t.insert(new Point2D(0.1, 0.1));
        t.insert(new Point2D(0.3, 0.7));
        t.insert(new Point2D(0.2, 0.2));    
        t.draw();
        
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.RED);        
        for(Point2D p : t.range(r)) {
            p.draw();
        }
        StdDraw.show();        
    }
}