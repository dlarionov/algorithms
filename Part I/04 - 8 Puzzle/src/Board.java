import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Board 
{
    private int n;
    private int[] arr;
    private int z;
    
    // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        n = blocks.length;
        int size = n*n;
        arr = new int[size];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int k = i*n+j;
                arr[k] = blocks[i][j];
                if (arr[k] == 0)
                    z = k;
            }
        }
    }
    
    public int dimension()
    {
        return n;
    }
    
    public int hamming() 
    {
        int cnt = 0;
        for(int i = 1; i <= arr.length; i++)
        {
            int j = arr[i-1];
            if (j == 0 || j == i)
                continue;
            cnt++;
        }
        return cnt;
    }
    
    public int manhattan() 
    {
        int sum = 0;
        for(int i = 1; i <= arr.length; i++)
        {
            int j = arr[i-1];
            if (j == 0 || j == i)
                continue;
            sum = sum + distance(i, j);
        }
        return sum;
    }
    
    private int distance(int goal, int value)
    {
        int dif = Math.abs(goal - value);
        return dif % n + dif / n;
    }
    
    public boolean isGoal() 
    {
        return hamming() == 0;
    }
    
    // TODO avoid zero
    public Board twin()
    {
        int[][] blocks = getCopy();
        int temp = blocks[0][0];
        blocks[0][0] = blocks[0][n-1];
        blocks[0][n-1] = temp;
        return new Board(blocks);        
    }
    
    private int[][] getCopy()
    {
        int[][] copy = new int[n][n];
        for (int i = 0; i < arr.length; i++)
        {
            copy[i/n][i%n] = arr[i];
        }
        return copy;
    }
    
    // TODO something wrong
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return this.n == that.n;
    }
    
    public Iterable<Board> neighbors() 
    { 
        Stack<Board> stack = new Stack<Board>();
        int i = z/n, j = z%n;
        int[][] copy = getCopy();
        
        if (i > 0) // top
        {
            copy[i][j] = copy[i-1][j];
            copy[i-1][j] = 0;
            stack.push(new Board(copy));
            copy[i-1][j] = copy[i][j];
            copy[i][j] = 0;
        }
        
        if (i < n-1) // bottom
        {
            copy[i][j] = copy[i+1][j];
            copy[i+1][j] = 0;
            stack.push(new Board(copy));
            copy[i+1][j] = copy[i][j];
            copy[i][j] = 0;
        }
        
        if (j < n-1) // right
        {
            copy[i][j] = copy[i][j+1];
            copy[i][j+1] = 0;
            stack.push(new Board(copy));
            copy[j][j+1] = copy[i][j];
            copy[i][j] = 0;
        }
        
        if (j > 0) // left
        {
            copy[i][j] = copy[i][j-1];
            copy[i][j-1] = 0;
            stack.push(new Board(copy));
            copy[i][j-1] = copy[i][j];
            copy[i][j] = 0;
        }  
        
        return stack;
    }
    
    public String toString() 
    {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j < n; j++) 
            {
                s.append(String.format("%2d ", arr[i*n+j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args)
    {
        Board board = new Board(new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} });
        int h = board.hamming();
        int m = board.manhattan();
        StdOut.println(h + " " + m);
    }
}