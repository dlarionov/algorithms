import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver
{
    private final BoggleTrie set;
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) 
            throw new IllegalArgumentException("argument is null");
        
        set = new BoggleTrie();
        for (String w : dictionary) {
            set.add(w);
        }
    }
    
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) 
            throw new IllegalArgumentException("argument to getAllValidWords() is null");        
        
        BoggleTrie words = new BoggleTrie();        
        for (int i = 0; i < board.cols(); i++) {
            for (int j = 0; j < board.rows(); j++) {
                dfs(board, i, j, words);
            }
        }
        return words.toList();
    }
    
    private void dfs(BoggleBoard board, int x, int y, BoggleTrie result) {
        dfs(board, x, y, new int[2][0], "", result);
    }
    
    private void dfs(BoggleBoard board, int x, int y, int[][] path, String prefix, BoggleTrie result) {
        int w = board.cols();
        int h = board.rows();
        
        if (x < 0 || y < 0 || x > w-1 || y > h-1)
            return;
        
        for (int i = 0; i < path[0].length; i++) {
            if (x == path[0][i] && y == path[1][i])
                return;
        }
        
        char letter = board.getLetter(x, y);
        prefix += letter == 'Q' ? letter + "U" : letter;
        
        if (!set.hasKeysWithPrefix(prefix))
            return;
        
        if (prefix.length() > 2 && set.contains(prefix))
            result.add(prefix);
        
        int[][] copy = new int[2][path[0].length + 1];
        System.arraycopy(path[0], 0, copy[0], 0, path[0].length);
        System.arraycopy(path[1], 0, copy[1], 0, path[1].length);
        copy[0][copy[0].length-1] = x;
        copy[1][copy[1].length-1] = y;
        
        dfs(board, x-1, y-1, copy, prefix, result);
        dfs(board, x, y-1, copy, prefix, result);
        dfs(board, x+1, y-1, copy, prefix, result);
        dfs(board, x-1, y, copy, prefix, result);
        dfs(board, x+1, y, copy, prefix, result);
        dfs(board, x-1, y+1, copy, prefix, result);
        dfs(board, x, y+1, copy, prefix, result);
        dfs(board, x+1, y+1, copy, prefix, result);        
    }    
    
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) 
            throw new IllegalArgumentException("argument to scoreOf() is null");
        
        if (set.contains(word)) {
            switch (word.length()) {
                case 0: return 0;
                case 1: return 0;
                case 2: return 0;
                case 3: return 1;
                case 4: return 1;
                case 5: return 2;
                case 6: return 3;
                case 7: return 5;
                default: return 11;
            }
        }
        return 0;
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
        // StdOut.println(board.toString());
    }
}