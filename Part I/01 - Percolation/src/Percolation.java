import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
  
  private boolean[][] states;  
  private WeightedQuickUnionUF connections;  
  private int totalSites;
  private int openSites;
  private int lastSite;
  private int dimension;
  
  // create n-by-n grid, with all sites blocked
  public Percolation(int n)
  {
    if (n  <  1)
      throw new java.lang.IllegalArgumentException();
    
    dimension = n;
    states = new boolean[n][n];    
    totalSites = n*n;
    openSites = 0;
    // create structure with two additional virtual elements: 0 and lastSite
    connections = new WeightedQuickUnionUF(totalSites+2);
    lastSite = totalSites+2-1;
    for (int i = 1; i <= n; i++)
    {
      // connect top row with first element
      connections.union(0, i);
      // connect bottom row with last element
      connections.union(lastSite, lastSite-i);
    }
  }
  
  private int getSite(int row, int col)
  {
    // first element is 1 (0 and lastSite are virtual elements)
    return (row-1)*dimension + col;
  }
  
  // open site (row, col) if it is not open already 
  public void open(int row, int col)
  {
    if (row < 1 || col < 1 || row > dimension || col > dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    int i = row-1;
    int j = col-1;
    
    if (states[i][j])
      return;
    
    states[i][j] = true;
    openSites++;
    
    int x = getSite(row, col);
    int n = dimension;
    
    // top
    if (i > 0 && states[i-1][j])
      connections.union(x, x-n);    
    
    // right
    if (j < n-1 && states[i][j+1])
      connections.union(x, x+1);
    
    // bottom
    if (i < n-1 && states[i+1][j])    
      connections.union(x, x+n);
    
    // left
    if (j > 0 && states[i][j-1])    
      connections.union(x, x-1);  
  }
  
  // is site (row, col) open?
  public boolean isOpen(int row, int col)
  {
    if (row < 1 || col < 1 || row > dimension || col > dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    return states[row-1][col-1];
  }
  
  // is site (row, col) full?
  public boolean isFull(int row, int col)
  {
    if (row < 1 || col < 1 || row > dimension || col > dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    return states[row-1][col-1] && connections.connected(0, getSite(row, col));
  }
  
  // number of open sites
  public int numberOfOpenSites()
  {
    return openSites;
  }
   
  // does the system percolate?
  public boolean percolates()
  {
    if (dimension == 1)
      return isOpen(1,1);
    
    return  connections.connected(0, lastSite);
  }
  
  public static void main(String[] args)
  {
    Percolation p = new Percolation(1);
    StdOut.println(p.percolates());
  }
}