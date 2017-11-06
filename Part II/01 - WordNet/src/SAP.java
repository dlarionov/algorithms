import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import java.util.ArrayList;

public class SAP 
{
    private final Graph graph;
    private final Digraph digraph;
    
    // constructor tak
    public SAP(Digraph G)
    {
        if (G == null || !new Topological(G).hasOrder())
        {
            throw new java.lang.IllegalArgumentException();
        }
        
        // todo check dag here
        graph = new Graph(G.V());
        digraph = new Digraph(G.V());
        
        for (int i = 0; i < G.V(); i++)
        {
            for(int j : G.adj(i)) 
            {
                graph.addEdge(i, j);
                digraph.addEdge(i, j);
            }
        }
    }    
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, v);        
        
        return paths.distTo(w);
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, v);
        Iterable<Integer> path = paths.pathTo(w);
        return ancestorByPath(path);
    }
    
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        validateVertices(v);
        validateVertices(w);
        
        int min = Integer.MAX_VALUE;
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, v);
        for(int i : w)
        {
            min = Math.min(min, paths.distTo(i));
        }

        return min;
    }
    
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        validateVertices(v);
        validateVertices(w);
        
        int min = Integer.MAX_VALUE;        
        Iterable<Integer> path = null;
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, v);
        for(int i : w)
        {
            int dist = paths.distTo(i);
            if (min > dist)
            {
                min = dist;
                path = paths.pathTo(i);
            }
        }
        
        return ancestorByPath(path);
    }
    
    private int ancestorByPath(Iterable<Integer> path)
    {
        if (path == null)
            return -1;
        
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i : path)
        {
            arr.add(i);
        }
        
        // todo use binary search
        int index = arr.size() - 1;
        while(index > 0 && contains(digraph.adj(arr.get(index)), arr.get(index-1)))
        {
            index--;
        }
        
        return arr.get(index);
    }
    
    private void validateVertex(int v) 
    {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    private void validateVertices(Iterable<Integer> vertices) 
    {
        if (vertices == null) 
        {
            throw new IllegalArgumentException("argument is null");
        }
        
        int V = graph.V();
        for (int v : vertices) 
        {
            if (v < 0 || v >= V) 
            {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    
    private static Boolean contains(Iterable<Integer> list, int x)
    {
        for (int i : list) 
        {
            if (x == i) 
                return true;
        }
        return false;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        Digraph x = new Digraph(10);
        x.addEdge(1, 0);
        x.addEdge(2, 1);
        x.addEdge(3, 1);
        x.addEdge(8, 1);
        x.addEdge(4, 2);
        x.addEdge(6, 2);
        x.addEdge(5, 4);
        x.addEdge(7, 3);
        x.addEdge(7, 8);
        x.addEdge(9, 7);
        SAP sap = new SAP(x);        
        // StdOut.println(sap.length(5, 9));
        StdOut.println(sap.ancestor(5, 9));
    }
}