import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class KdTree {
    private Node root;
    private int size;
    
    public KdTree() {
        
    }
    
    private class Node {
        private Node left;
        private Node right;
        private boolean odd;
        private Point2D point;
        
        public Node(Point2D p) {
            point = p;
        }
        
        public int compareToPoint(Point2D p) {
            int r;
            if (this.point.equals(p))
                r = 0;
            else if (this.odd)
                r = Double.compare(this.point.y(), p.y()) < 0 ? -1 : 1;
            else
                r = Double.compare(this.point.x(), p.x()) < 0 ? -1 : 1;
            return r;
        }
        
        public int compareToRect(RectHV rect) {
            int r;
            if (this.odd) {
                if (Double.compare(this.point.y(), rect.ymin()) < 0) r = -1;
                else if (Double.compare(this.point.y(), rect.ymax()) > 0) r = 1;
                else r = 0;
            }
            else {
                if (Double.compare(this.point.x(), rect.xmin()) < 0) r = -1;
                else if (Double.compare(this.point.x(), rect.xmax()) > 0) r = 1;
                else r = 0;
            }
            return r;
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
    
    private void draw(Node node) {
        if (node == null)
            return;
        node.point.draw();
        draw(node.left);
        draw(node.right);
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new java.lang.NullPointerException();
        
        Stack<Point2D> stack = new Stack<Point2D>();        
        range(rect, root, stack);        
        return stack;
    }
    
    private void range(RectHV rect, Node node, Stack<Point2D> res) {
        if (node == null)
            return;
        
        int cmp = node.compareToRect(rect);
        if (cmp < 0)
            range(rect, node.left, res);
        else if (cmp > 0)
            range(rect, node.right, res);
        else {
            range(rect, node.left, res);
            range(rect, node.right, res);
            
            if (node.odd) {
                double x = node.point.x();
                if (x > rect.xmin() && x < rect.xmax())
                    res.push(node.point);
            }
            else {
                double y = node.point.y();
                if (y > rect.ymin() && y < rect.ymax())
                    res.push(node.point);
            }
        }
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();
        
        // nearest(p, root);
        
        return null;
    }
    
    private Point2D nearest(Point2D point, Node node, RectHV rect, double distance, Point2D champion)
    {
        if (node == null)
            return champion;
        
        int cmp = node.compareToRect(rect);
        if (cmp < 0)
            nearest(point, node.left, rect, distance, champion);
        else if (cmp > 0)
            nearest(point, node.left, rect, distance, champion);
        else {
            nearest(point, node.left, rect, distance, champion);
            nearest(point, node.left, rect, distance, champion);
        }
        
        rerurn champion;
    }
    
    public static void main(String[] args) {
        StdDraw.setPenRadius(.001);
        StdDraw.setPenColor(StdDraw.GRAY);
        RectHV r = new RectHV(0.2, 0.2, 0.8, 0.8);
        r.draw();
        
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        KdTree t = new KdTree();
        for (int i = 0; i < 10000; i++)
            t.insert(new Point2D(StdRandom.uniform(0.0, 1.0), StdRandom.uniform(0.0, 1.0)));
        t.draw();
        
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.RED);        
        for(Point2D p : t.range(r)) {
            p.draw();
        }
        StdDraw.show();        
    }
}