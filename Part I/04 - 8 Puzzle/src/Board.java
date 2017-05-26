import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

public class Board 
{
    private int n;
    private int[][] arr;
    private int z;
    private int hamming;
    private int manhattan;
    
    public Board(int[][] blocks)
    {
        if (blocks == null)
            throw new java.lang.NullPointerException();
        
        n = blocks.length;
        arr = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int k = i*n+j;
                int x = blocks[i][j];
                if (x == 0) 
                    z = k;
                else if (x != k+1)
                {
                    hamming++;
                    manhattan += distance(k+1, x);
                }
                arr[i][j] = x;
            }
        }
    }
    
    private int distance(int a, int b)
    {
        int i = Math.abs(((a-1) / n) - ((b-1) / n));
        int j = Math.abs(((a-1) % n) - ((b-1) % n));
        return i+j;
    }
    
    public int dimension()
    {
        return n;
    }
    
    public int hamming() 
    {
        return hamming;
    }
    
    public int manhattan() 
    {
        return manhattan;
    }
    
    public boolean isGoal() 
    {
        return hamming == 0;
    }
    
    public Board twin()
    {
        if (n < 2)
            return new Board(arr);
        
        Board board;
        if (z < n)
        {
            int x = arr[1][0];
            arr[1][0] = arr[1][1];
            arr[1][1] = x;
            board = new Board(arr);
            arr[1][1] = arr[1][0];
            arr[1][0] = x;            
        }
        else
        {
            int x = arr[0][0];
            arr[0][0] = arr[0][1];
            arr[0][1] = x;
            board = new Board(arr);
            arr[0][1] = arr[0][0];
            arr[0][0] = x;            
        }
        
        return board;        
    }    
    
    public boolean equals(Object y)
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;        
        if (this.n != that.n) return false;        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (this.arr[i][j] != that.arr[i][j]) // WTF !?
                    return false;
            }            
        }
        return true;
    }
    
    public Iterable<Board> neighbors()
    { 
        Stack<Board> stack = new Stack<Board>();
        if (n < 2)
            return stack;
        
        int i = z / n, j = z % n;
                        
        if (i > 0) // top
        {
            arr[i][j] = arr[i-1][j];
            arr[i-1][j] = 0;
            stack.push(new Board(arr));
            arr[i-1][j] = arr[i][j];
            arr[i][j] = 0;
        }
        
        if (i < n-1) // bottom
        {
            arr[i][j] = arr[i+1][j];
            arr[i+1][j] = 0;
            stack.push(new Board(arr));
            arr[i+1][j] = arr[i][j];
            arr[i][j] = 0;
        }
        
        if (j < n-1) // right
        {
            arr[i][j] = arr[i][j+1];
            arr[i][j+1] = 0;
            stack.push(new Board(arr));
            arr[i][j+1] = arr[i][j];
            arr[i][j] = 0;
        }
        
        if (j > 0) // left
        {
            arr[i][j] = arr[i][j-1];
            arr[i][j-1] = 0;
            stack.push(new Board(arr));
            arr[i][j-1] = arr[i][j];
            arr[i][j] = 0;
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
                s.append(String.format("%2d ", arr[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args)
    {
        Board board = new Board(new int[][] { {5, 8, 7}, {1, 4, 6}, {3, 0, 2} });        
        StdOut.println("manhattan:"+board.manhattan());
        StdOut.print(board);
    }
}