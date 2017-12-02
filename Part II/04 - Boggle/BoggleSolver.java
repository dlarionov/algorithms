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
        for(String w : dictionary) {
            set.add(w);
        }
    }
    
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) 
            throw new IllegalArgumentException("argument to getAllValidWords() is null");        
        
        BoggleTrie result = new BoggleTrie();        
        for (int i = 0; i < board.cols(); i++) {
            for (int j = 0; j < board.rows(); j++) {
                dfs(board, i, j, result);
            }
        }        
        return result.toList();
    }
    
    private void dfs(BoggleBoard board, int x, int y, BoggleTrie result) {
        boolean[][] marked = new boolean[board.cols()][board.rows()];        
        int[][] path = new int[2][0];
        dfs(board, x, y, path, "", result);
    }
    
    private void dfs(BoggleBoard board, int x, int y, BoggleTrie result, boolean[][] marked, String prefix) {
        int w = board.cols();
        int h = board.rows();
        
        if (x < 0 || y < 0 || x > w-1 || y > h-1 || marked[x][y])
            return;
               
        prefix += board.getLetter(x, y);
        marked[x][y] = true;
        
        if (!set.hasKeysWithPrefix(prefix))
            return;
        
        if (prefix.length() > 2 && set.contains(prefix))
            result.add(prefix);
        
        dfs(board, x-1, y-1, result, marked, prefix);
        dfs(board, x, y-1, result, marked, prefix);
        dfs(board, x+1, y-1, result, marked, prefix);
        dfs(board, x-1, y, result, marked, prefix);
        dfs(board, x+1, y, result, marked, prefix);
        dfs(board, x-1, y+1, result, marked, prefix);
        dfs(board, x, y+1, result, marked, prefix);
        dfs(board, x+1, y+1, result, marked, prefix);        
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
        StdOut.println(board.toString());
    }
}