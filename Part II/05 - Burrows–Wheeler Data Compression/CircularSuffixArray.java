import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

public class CircularSuffixArray {
    private final int[] index;
    
    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new java.lang.IllegalArgumentException();
        
        index = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            
        }        
    }
    
    // length of s
    public int length() {
        return index.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > index.length - 1)
            throw new java.lang.IllegalArgumentException();        
        return index[i];
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray sfx = new CircularSuffixArray(s);
        StdOut.println(sfx.length());
        StdOut.println(sfx.index(sfx.length() / 2));
    }
}