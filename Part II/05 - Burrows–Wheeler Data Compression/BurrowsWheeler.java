import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import java.util.Arrays;

public class BurrowsWheeler {
    private static final int CHAR_BITS = 8;
    private static final int R = 256;
    
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray sfx = new CircularSuffixArray(s);
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            if (sfx.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        
        for (int i = 0; i < len; i++) {
            int j = sfx.index(i);
            if (j == 0) BinaryStdOut.write(s.charAt(len-1), CHAR_BITS);
            else        BinaryStdOut.write(s.charAt(j - 1), CHAR_BITS);
        }
        
        BinaryStdOut.close();        
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        int len = s.length();
        
        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);
        
        int[] count = new int[R];
        int[] next = new int[len];
        
        for (int i = 0; i < len; i++) {
            int x = sorted[i];
            for (int j = count[x]; j < len; j++) {
                if (x == s.charAt(j)) {
                    count[x] = j+1; // next time start from j+1
                    next[i] = j;
                    break;
                }
            }
        }
        
        for (int i = 0, j = first; i < len; i++, j = next[j]) {
            BinaryStdOut.write(sorted[j], CHAR_BITS);
        }
        
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if      (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}