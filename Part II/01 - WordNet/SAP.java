import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;

public class SAP 
{
    private final Digraph digraph;
    
    // constructor tak
    public SAP(Digraph G) {
        if (G == null)
            throw new java.lang.IllegalArgumentException();       
        
        digraph = copy(G);
    }
    
    private Digraph copy(Digraph G) {
        Digraph copy = new Digraph(G.V());        
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) 
                copy.addEdge(i, j);
        }
        return copy;
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return new DeluxeBFS(digraph, v, w).distance();
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
       return new DeluxeBFS(digraph, v, w).ancestor();
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
       return new DeluxeBFS(digraph, v, w).distance();
    }
    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return new DeluxeBFS(digraph, v, w).ancestor();
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) 
        {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}