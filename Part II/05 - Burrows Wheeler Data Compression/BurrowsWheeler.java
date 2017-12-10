import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MSD;

public class BurrowsWheeler {
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
        
        char[] ch = new char[len];
        for (int i = 0; i < len; i++) {
            int j = sfx.index(i);
            if (j == 0) ch[i] = s.charAt(len-1);
            else        ch[i] = s.charAt(j - 1);
        }
        
        BinaryStdOut.write(new String(ch));
        BinaryStdOut.close();        
    }
    
    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() { 
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        int len = s.length();
        
        int[] copy = new int[len];
        for (int i = 0; i < len; i++)
            copy[i] = s.charAt(i);
        MSD.sort(copy);
        
        int[] count = new int[256];
        int[] next = new int[len];
        
        // todo inverseTransform() is possibly n*R or n log n
        for (int i = 0; i < len; i++) {
            int x = copy[i];
            for (int j = count[x]; j < len; j++) {
                if (x == s.charAt(j)) {
                    count[x] = j+1; // next time start from j+1
                    next[i] = j;
                    break;
                }
            }
        }
        
        char[] ch = new char[len];
        for (int i = 0, j = first; i < len; i++, j = next[j])
            ch[i] = (char) copy[j];
        
        BinaryStdOut.write(new String(ch));
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