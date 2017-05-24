import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver 
{
    private boolean isSolvable;
    private Transform solution;
    private MinPQ<Transform> pq;
    
    public Solver(Board initial)
    {
        if (initial == null)
            throw new java.lang.NullPointerException();
        
        Transform root = new Transform(initial, 0, null);
        pq.insert(root);        
        move(root);
        
        while(!pq.isEmpty())
        {
            Transform t = pq.delMin();
            Board b = t.board();
            if (b.isGoal())
            {
                solution = t;
                break;
            }
            move(t);
        }
    }
    
    private void move(Transform prev)
    {
        int age = prev.age()+1;
        for(Board board : prev.board.neighbors())
        {
            pq.insert(new Transform(board, age, prev));
        }
    }
    
    private class Transform implements Comparable<Transform>
    {
        private Board board;
        private int age;
        private Transform prev;
        
        public Transform(Board board, int age, Transform prev)
        {
            this.board =  board;
            this.age = age;
            this.prev = prev;
        }
        
        public int compareTo(Transform that)
        { 
            return this.priority() - that.priority();
        }
        
        public int priority()
        {
            return this.board.manhattan() + this.age;
        }
        
        public Board board()
        {
            return this.board;
        }
        
        public int age()
        {
            return this.age;
        }
        
        public Transform prev()
        {
            return this.prev;
        }
    }
    
    public boolean isSolvable()
    {
        return isSolvable;
    }
    
    public int moves()
    {
        if (!isSolvable)
            return -1;
        
        return solution.age();
    }
    
    public Iterable<Board> solution()
    {
        if (!isSolvable)
            return null;
        
        Stack<Board> stack = new Stack<Board>();
        Transform x = solution;
        while(x != null)
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