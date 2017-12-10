import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import java.util.LinkedList;

public class MoveToFront {
    private static final int CHAR_BITS = 8;
    private static final int R = 256;
    
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        LinkedList<Integer> chain = initChain();
        
        // todo use stream
        String s = BinaryStdIn.readString();
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i);
            int idx = chain.indexOf(x);
            BinaryStdOut.write((char) idx, CHAR_BITS);
            int obj = chain.remove(idx);
            chain.add(0, obj);
        }
        
        BinaryStdOut.close();
    }
    
    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        LinkedList<Integer> chain = initChain();
        
        // todo use stream
        String s = BinaryStdIn.readString();
        for (int i = 0; i < s.length(); i++) {
            int obj = chain.remove(s.charAt(i));
            chain.add(0, obj);
            BinaryStdOut.write((char) obj, CHAR_BITS);
        }
        
        BinaryStdOut.close();
    }
    
    private static LinkedList<Integer> initChain() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < R; i++) {
            list.add(i);
        }
        return list;
    }
    
    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}