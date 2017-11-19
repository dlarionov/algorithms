import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {
    private Node root;
    private int size;
    
    public KdTree() {
    }
    
    private class Node {
        public Node left;
        public Node right;
        public boolean odd;
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
        while (true)
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
        while (x != null)
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
    
    private void range(RectHV rect, Node node, Stack<Point2D> stack) {
        if (node == null)
            return;
        
        int cmp = node.compareToRect(rect);
        if (cmp < 0)
            range(rect, node.left, stack);
        else if (cmp > 0)
            range(rect, node.right, stack);
        else {
            range(rect, node.left, stack);
            range(rect, node.right, stack);
            
            if (node.odd) {
                double x = node.point.x();
                if (x >= rect.xmin() && x <= rect.xmax())
                    stack.push(node.point);
            }
            else {
                double y = node.point.y();
                if (y >= rect.ymin() && y <= rect.ymax())
                    stack.push(node.point);
            }
        }
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new java.lang.NullPointerException();        
        return new NearestSearch(p, root).champion();
    }
    
    private class NearestSearch {
        private Point2D point;
        private Point2D champion;
        private double distance;        
        
        public NearestSearch(Point2D p, Node node) {
            point = p;
            distance = Double.POSITIVE_INFINITY;
            search(node);
        }
        
        private void search(Node node) {
            if (node == null)
                return;
            
            RectHV square = zoom();
            if (square.contains(node.point)) {
                double value = node.point.distanceSquaredTo(point);
                if (value < distance*distance) {
                    distance = Math.sqrt(value);
                    champion = node.point;
                    square = zoom();
                }
            }
            
            int cmp = node.compareToRect(square);
            if (cmp < 0)
                search(node.left);
            else if (cmp > 0)
                search(node.right);
            else {
                boolean goLeft = node.compareToPoint(point) < 0;
                if (goLeft)
                    search(node.left);
                else
                    search(node.right);
                
                square = zoom();
                cmp = node.compareToRect(square);
                if (cmp == 0) {
                    if (!goLeft)
                        search(node.left);
                    else
                        search(node.right);
                }                
            }
        }
        
        private RectHV zoom() {
            return new RectHV(point.x()-distance, point.y()-distance, point.x()+distance, point.y()+distance);
        }
        
        public Point2D champion() {
            return champion;
        }
    }
    
    public static void main(String[] args) {

    }
}