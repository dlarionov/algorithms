import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashSet;

public class BoggleSolver
{
    private static final int R = 26;
    private static final int D = 65; // offset of A
    
    private final Trie set;
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException("argument is null");
        
        set = new Trie();
        for (String w : dictionary) {
            set.add(w);
        }
    }
    
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }
    
    private class Trie
    {
        private Node root;
        
        public Node root() {
            return root;
        }
        
        public Node get(String key) {
            return get(root, key, 0);
        }        
        
        public Node get(Node x, String key) {
            return get(x, key, 0);
        }
        
        private Node get(Node x, String key, int d) {
            if (x == null) return null;
            if (d == key.length()) return x;
            int c = key.charAt(d);
            // if (c < D || c > D + R - 1) return null;
            return get(x.next[c-D], key, d+1);
        }
        
        public void add(String key) {
            if (key == null) throw new IllegalArgumentException("argument to add() is null");
            root = add(root, key, 0);
        }
        
        private Node add(Node x, String key, int d) {
            if (x == null) x = new Node();
            if (d == key.length()) {
                x.isString = true;
            }
            else {
                int c = key.charAt(d);
                // if (c < D || c > D + R - 1) 
                //    throw new IllegalArgumentException("argument to add() contains letter that is not in the alphabet");
                x.next[c-D] = add(x.next[c-D], key, d+1);
            }
            return x;
        }
    }    
    
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) 
            throw new IllegalArgumentException("argument to getAllValidWords() is null");
        
        int w = board.cols();
        int h = board.rows();
        
        HashSet<String> words = new HashSet<String>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // todo write an interactive version
                dfs(board, i, j, new int[2][0], set.root(), "", words);
            }
        }
        return words;
    }
    
    private void dfs(BoggleBoard board, int x, int y, int[][] path, Node node, String prefix, HashSet<String> result) {
        int w = board.cols();
        int h = board.rows();
        
        if (x < 0 || y < 0 || x > w-1 || y > h-1)
            return;
        
        for (int i = 0; i < path[0].length; i++) {
            if (x == path[0][i] && y == path[1][i])
                return;
        }
        
        String letter = String.valueOf(board.getLetter(y, x));
        if (letter.equals("Q")) {
            letter += "U";            
        }
        node = set.get(node, letter);
        if (node == null)
            return;
        
        prefix += letter;
        if (prefix.length() > 2 && node.isString && !result.contains(prefix))
            result.add(prefix);
        
        // todo optimize path processing
        int[][] copy = new int[2][path[0].length + 1];
        System.arraycopy(path[0], 0, copy[0], 0, path[0].length);
        System.arraycopy(path[1], 0, copy[1], 0, path[1].length);
        copy[0][copy[0].length-1] = x;
        copy[1][copy[1].length-1] = y;
        
        dfs(board, x-1, y-1, copy, node, prefix, result);
        dfs(board, x, y-1, copy, node, prefix, result);
        dfs(board, x+1, y-1, copy, node, prefix, result);
        dfs(board, x-1, y, copy, node, prefix, result);
        dfs(board, x+1, y, copy, node, prefix, result);
        dfs(board, x-1, y+1, copy, node, prefix, result);
        dfs(board, x, y+1, copy, node, prefix, result);
        dfs(board, x+1, y+1, copy, node, prefix, result);
    }
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) 
            throw new IllegalArgumentException("argument to scoreOf() is null");
        
        Node x = set.get(word);
        if (x == null || !x.isString) 
            return 0;
        
        switch (word.length()) {
            case 0:
            case 1:
            case 2: return 0;
            case 3:
            case 4: return 1;
            case 5: return 2;
            case 6: return 3;
            case 7: return 5;
            default: return 11;
        }
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;       
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}