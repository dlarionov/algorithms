import edu.princeton.cs.algs4.StdOut;

public class BoggleTrie {
    private static final int R = 26;
    private static final int offset = 65;
    
    private Node root;
    private int n;
    
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }
    
    public BoggleTrie() {
    }
    
    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.isString;
    }
    
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = key.charAt(d);
        // if (c < offset || c > offset + R - 1) return null;
        return get(x.next[c-offset], key, d+1);
    }
    
    public void add(String key) {
        if (key == null) throw new IllegalArgumentException("argument to add() is null");
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (!x.isString) n++;
            x.isString = true;
        }
        else {
            int c = key.charAt(d);
            // if (c < offset || c > offset + R - 1) throw new IllegalArgumentException("argument to add() contains letter that is not in the alphabet");                
            x.next[c-offset] = add(x.next[c-offset], key, d+1);
        }
        return x;
    }
    
    public int size() {
        return n;
    }
    
//    public Iterable<String> keysWithPrefix(String prefix) {
//        Queue<String> results = new Queue<String>();
//        Node x = get(root, prefix, 0);
//        collect(x, new StringBuilder(prefix), results);
//        return results;
//    }
//
//    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
//        if (x == null) return;
//        if (x.isString) results.enqueue(prefix.toString());
//        for (char c = 0; c < R; c++) {
//            prefix.append(c);
//            collect(x.next[c], prefix, results);
//            prefix.deleteCharAt(prefix.length() - 1);
//        }
//    }
    
     public static void main(String[] args) {
         String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         for (int i = 0; i < ALPHABET.length(); i++){
             int c = ALPHABET.charAt(i);        
             StdOut.println(c);
         }
     }
}