import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver 
{
    private Transform solution;
    private MinPQ<Transform> pq;
    
    public Solver(Board initial)
    {
        if (initial == null)
            throw new java.lang.NullPointerException();
        
        pq = new MinPQ<Transform>();
        pq.insert(new Transform(initial, null));
        pq.insert(new Transform(initial.twin(), null));
        while (!pq.isEmpty())
        {
            Transform t = pq.delMin();
            if (t.board().isGoal())
            {
                Transform x = t;
                while (x.prev() != null)
                {
                    x = x.prev();
                }
                if (x.board().equals(initial))
                    solution = t;
                break;
            }
            move(t);
        }
    }
    
    private void move(Transform prev)
    {
        for (Board b : prev.board.neighbors())
        {
            if (!prev.hasBoard(b))
                pq.insert(new Transform(b, prev));
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
            age = t == null ? 0 : t.age()+1;
            priority = b.manhattan() + age;
        }
        
        public int compareTo(Transform that)
        { 
            return this.priority() - that.priority();
        }
        
        public boolean hasBoard(Board b)
        {
            Transform x = prev;
            while (x != null)
            {
                if (x.board().equals(b))
                    return true;
                x = x.prev();
            }
            return false;
        }
        
        public int priority()
        {
            return priority;
        }
        
        public Board board()
        {
            return board;
        }
        
        public int age()
        {
            return age;
        }
        
        public Transform prev()
        {
            return prev;
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
        
        return solution.age();
    }
    
    public Iterable<Board> solution()
    {
        if (solution == null)
            return null;
        
        Stack<Board> stack = new Stack<Board>();
        Transform x = solution;
        while (x != null)
        {
            stack.push(x.board());
            x = x.prev();
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