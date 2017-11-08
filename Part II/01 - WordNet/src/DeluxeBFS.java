import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.StdOut;

public class DeluxeBFS 
{
    private final int n;
    
    private int ancestor = -1;
    private int distance = Integer.MAX_VALUE;
    
    public DeluxeBFS(Digraph G, int s, int t) {
        n = G.V();
        validateVertex(s);
        validateVertex(t);
        bfs(G, s, t);
    }
    
    public DeluxeBFS(Digraph G, Iterable<Integer> s, Iterable<Integer> t) {
        n = G.V();        
        validateVertices(s);
        validateVertices(t);        
        bfs(G, s, t);
    }
    
    private void bfs(Digraph G, int s, int t) {
        if (s == t) {
            ancestor = s;
            distance = 0;
            return;
        }
        
        Queue<Integer> q = new Queue<Integer>();        
        boolean[] m1 = new boolean[n];
        boolean[] m2 = new boolean[n];
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        
        m1[s] = true;        
        q.enqueue(s);
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!m1[w]) {
                    if (w == t) {
                        distance = d1[v] + 1;
                        ancestor = w;
                    }
                    
                    m1[w] = true;
                    d1[w] = d1[v] + 1;
                    q.enqueue(w);
                }
            }
        }
        
        m2[t] = true;
        q.enqueue(t);
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!m2[w])  {
                    if (m1[w]) {
                        int d = d2[v] + d1[w] + 1;
                        if (d < distance) {
                            distance = d;
                            ancestor = w;
                        }
                    }
                    
                    m2[w] = true;
                    d2[w] = d2[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }
    
    private void bfs(Digraph G, Iterable<Integer> s, Iterable<Integer> t) {                
        for (int i: s) {
            for (int j : t) {
                if (i == j) {
                    ancestor = i;
                    distance = 0;
                    return;
                }
            }
        }
        
        Queue<Integer> q = new Queue<Integer>();        
        boolean[] m1 = new boolean[n];
        boolean[] m2 = new boolean[n];
        int[] d1 = new int[n];
        int[] d2 = new int[n];
        
         for (int i : s) {
            m1[i] = true;
            q.enqueue(i);
        }
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!m1[w]) {
                    for (int j : t) {
                        if (w == j) {
                            int d = d1[v] + 1;
                            if (d < distance) {
                                distance = d;
                                ancestor = w;
                            }
                        }
                    }
                    
                    m1[w] = true;
                    d1[w] = d1[v] + 1;
                    q.enqueue(w);
                }
            }
        }
        
       for (int j : t) {
            m2[j] = true;
            q.enqueue(j);
        }
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!m2[w])  {
                    if (m1[w]) {
                        int d = d2[v] + d1[w] + 1;
                        if (d < distance) {
                            distance = d;
                            ancestor = w;
                        }
                    }
                    
                    m2[w] = true;
                    d2[w] = d2[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }
    
    public int ancestor() { return ancestor; }
    public int distance() { return ancestor < 0 ? -1 : distance; }
    
    private void validateVertex(int v) {
        if (v < 0 || v >= n)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (n-1));
    }
    
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null)
            throw new IllegalArgumentException("argument is null");
        
        for (int v : vertices) {
            if (v < 0 || v >= n)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (n-1));            
        }
    }
}