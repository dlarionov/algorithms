import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver 
{
    private Transform solution;    
    
    public Solver(Board initial)
    {
        if (initial == null)
            throw new java.lang.NullPointerException();
        
        MinPQ<Transform> pq1 = new MinPQ<Transform>();
        MinPQ<Transform> pq2 = new MinPQ<Transform>();
        pq1.insert(new Transform(initial, null));
        pq2.insert(new Transform(initial.twin(), null));
        while (true)
        {
            Transform t1 = pq1.delMin();
            if (t1.board.isGoal())
            {
                solution = t1;
                break;
            }
            
            Transform t2 = pq2.delMin();
            if (t2.board.isGoal())
                break;
            
            move(pq1, t1);
            move(pq2, t2);
        }
    }
    
    private void move(MinPQ<Transform> pq, Transform t)
    {
        for (Board b : t.board.neighbors())
        {
            if (!t.hasBoard(b))
                pq.insert(new Transform(b, t));
        }
    }
    
    private class Transform implements Comparable<Transform>
    {
        private Board board;
        private int age;
        private int priority;
        private Transform prev;
        
        public Transform(Board b, Transform t)
        {
            if (b == null)
                throw new java.lang.NullPointerException();
            
            board = b;
            prev = t;
            age = t == null ? 0 : t.age+1;
            priority = b.manhattan() + age;
        }
        
        public int compareTo(Transform that)
        { 
            return this.priority - that.priority;
        }
        
        public boolean hasBoard(Board b)
        {
            Transform x = prev;
            while (x != null)
            {
                if (x.board.equals(b))
                    return true;
                x = x.prev;
            }
            return false;
        }
    }
    
    public boolean isSolvable()
    {
        return solution != null;
    }
    
    public int moves()
    {
        if (solution == null)
            return -1;
        
        return solution.age;
    }
    
    public Iterable<Board> solution()
    {
        if (solution == null)
            return null;
        
        Stack<Board> stack = new Stack<Board>();
        Transform x = solution;
        while (x != null)
        {
            stack.push(x.board);
            x = x.prev;
        }
        return stack;
    }
    
    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}