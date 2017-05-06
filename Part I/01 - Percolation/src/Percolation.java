import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {
  
  private boolean[][] _states;  
  private WeightedQuickUnionUF _connections;
  
  // create n-by-n grid, with all sites blocked
  public Percolation(int n)
  {
    // it's OK to use an extra row and/or column to deal with the 1-based indexing of the percolation grid.
    _states = new boolean[n+1][n+1];    
    _connections = new WeightedQuickUnionUF(n^2);
  }
  
  // open site (row, col) if it is not open already 
  public void open(int row, int col)
  {
    _states[row][col] = !_states[row][col];
  }
  
  // is site (row, col) open?
  public boolean isOpen(int row, int col)
  {
    return _states[row][col];
  }
  
  // is site (row, col) full?
  // public boolean isFull(int row, int col)
  
  // number of open sites
  //public int numberOfOpenSites()
   
  // does the system percolate?
  //public boolean percolates()              

  public static void main(String[] args)
  {
    Percolation p = new Percolation(100);
    p.open(2,3);
    //StdOut.print(p.isOpen(3,2));
    //StdOut.print(p.isOpen(2,3));
    
  }
}