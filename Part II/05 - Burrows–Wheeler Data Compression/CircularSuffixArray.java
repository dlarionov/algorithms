import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class CircularSuffixArray {
    private final int[] index;
    
    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new java.lang.IllegalArgumentException();
        
        int len = s.length();
        
        SuffixNode[] arr = new SuffixNode[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new SuffixNode(s, i);
        }
        
        // TODO use MSD
        Arrays.sort(arr);
        
        index = new int[len];
        for (int i = 0; i < len; i++) {
            index[i] = arr[i].offset();
        }
    }
    
    private class SuffixNode implements Comparable<SuffixNode> {
        private final String s;
        private final int offset;
        
        public SuffixNode(String s, int offset) {
            this.s = s;
            this.offset = offset;
        }
        
        public int offset() {
            return offset;
        }
        
        public int compareTo(SuffixNode that) {
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char a = s.charAt((i + this.offset) % len);
                char b = s.charAt((i + that.offset) % len);
                if (a > b) return 1;
                else if (a < b) return -1;
                else continue;
            }
            return 0;
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
        String s = args[0];
        CircularSuffixArray sfx = new CircularSuffixArray(s);
        int len = sfx.length();
        for (int i = 0; i < len; i++) {
            StdOut.println(sfx.index(i));
        }
    }
}