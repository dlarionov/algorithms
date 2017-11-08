import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.StdOut;

public class DeluxeBFS 
{
    private final int n;
    
    private int ancestor = -1;
    private int distance = Integer.MAX_VALUE;
    
    public int ancestor() { return ancestor; }
    public int distance() { return ancestor < 0 ? -1 : distance; }
    
    public DeluxeBFS(Digraph G, int s, int t) 
    {
        n = G.V();
        
        validateVertex(s);
        validateVertex(t);
        
        bfs(G, s, t);
    }

//    public DeluxeBFS(Digraph G, Iterable<Integer> sources) 
//    {
//        marked = new boolean[G.V()];
//        distTo = new int[G.V()];
//        edgeTo = new int[G.V()];
//        for (int v = 0; v < G.V(); v++)
//            distTo[v] = INFINITY;
//        validateVertices(sources);
//        bfs(G, sources);
//    }

    private void bfs(Digraph G, int s, int t) 
    {
        if (s == t)
        {
            ancestor = s;
            distance = 0;
            return;
        }
        
        Queue<Integer> q = new Queue<Integer>();
        
        boolean[] m1 = new boolean[n];
        boolean[] m2 = new boolean[n];
        int[] distTo = new int[n];
        
        m1[s] = true;                
        m2[t] = true;        
        q.enqueue(s);
        q.enqueue(t);       
        
        while (!q.isEmpty()) 
        {
            int v = q.dequeue();
            
            for (int w : G.adj(v))
            {
                if ((m1[v] && m1[w]) || (!m1[v] && m2[w]))
                    break;
                
                if ((m1[v] && m2[w]) || (!m1[v] && m1[w]))
                {
                    int d = distTo[v] + distTo[w] + 1;
                    if (d < distance)
                    {
                        distance = d;
                        ancestor = w;
                    }
                }
                
                if (m1[v])
                    m1[w] = true;
                else
                    m2[w] = true;
                distTo[w] = distTo[v] + 1;
                q.enqueue(w);                
            }
            
            // m1[v] - true for s and false for t
//            if (m1[v])
//            {
//                for (int w : G.adj(v))
//                {
//                    if (m2[w])
//                    {
//                        ancestor = w;
//                        distance = distTo[v] + distTo[w] + 1;
//                        return;
//                    }
//                    else
//                    {
//                        m1[w] = true;
//                        distTo[w] = distTo[v] + 1;
//                        q.enqueue(w);
//                    }
//                }
//            }
//            else // if (m2[v])
//            {
//                for (int w : G.adj(v))
//                {
//                    if (m1[w])
//                    {
//                        ancestor = w;
//                        distance = distTo[v] + distTo[w] + 1;
//                        return;
//                    }
//                    else
//                    {
//                        m2[w] = true;
//                        distTo[w] = distTo[v] + 1;
//                        q.enqueue(w);
//                    }
//                }
//            }
        }
    }   

    // BFS from multiple sources
//    private void bfs(Digraph G, Iterable<Integer> sources) {
//        Queue<Integer> q = new Queue<Integer>();
//        for (int s : sources) {
//            marked[s] = true;
//            distTo[s] = 0;
//            q.enqueue(s);
//        }
//        while (!q.isEmpty()) {
//            int v = q.dequeue();
//            for (int w : G.adj(v)) {
//                if (!marked[w]) {
//                    edgeTo[w] = v;
//                    distTo[w] = distTo[v] + 1;
//                    marked[w] = true;
//                    q.enqueue(w);
//                }
//            }
//        }
//    }

    private void validateVertex(int v) 
    {
        if (v < 0 || v >= n)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (n-1));
    }

    private void validateVertices(Iterable<Integer> vertices) 
    {
        if (vertices == null) 
        {
            throw new IllegalArgumentException("argument is null");
        }
        for (int v : vertices) 
        {
            if (v < 0 || v >= n) 
            {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (n-1));
            }
        }
    }
}