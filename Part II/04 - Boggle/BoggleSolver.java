// import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver
{
    private final BoggleTrie trie;
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) throw new IllegalArgumentException("argument is null");
        trie = new BoggleTrie();
        for(String word : dictionary) {
            trie.add(word);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) throw new IllegalArgumentException("argument to getAllValidWords() is null");
        
        int h = board.rows();
        int w = board.cols();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // todo dfs each cell
            }
        }
        
        return null;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) throw new IllegalArgumentException("argument to scoreOf() is null");        
        if (trie.contains(word)) {
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
}