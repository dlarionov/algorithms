import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdIn;

public class Percolation {
  
  private boolean[][] _states;  
  private WeightedQuickUnionUF _connections;  
  private int _totalSites;
  private int _openSites;
  private int _lastSite;
  private int _dimension;
  
  // create n-by-n grid, with all sites blocked
  public Percolation(int n)
  {
    if (n < 1)
      throw new java.lang.IllegalArgumentException();
    
    _dimension = n;
    _states = new boolean[n][n];    
    _totalSites = n*n;
    _openSites = 0;
    // create structure with two additional virtual elements: 0 and _lastSite
    _connections = new WeightedQuickUnionUF(_totalSites+2);
    _lastSite = _totalSites+2-1;
    for(int i = 1; i <= n; i++)
    {
      // connect top row with first element
      _connections.union(0, i);
      // connect bottom row with last element
      _connections.union(_lastSite, _lastSite-i);
    }
  }
  
  private int getSite(int row, int col)
  {
    // first element is 1 (0 and _lastSite are virtual elements)
    return (row-1)*_dimension + col;
  }
  
  // open site (row, col) if it is not open already 
  public void open(int row, int col)
  {
    if (row < 1 || col < 1 || row > _dimension || col > _dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    int i = row-1;
    int j = col-1;
    
    if (_states[i][j])
      return;
    
    _states[i][j] = true;
    _openSites++;
    
    int x = getSite(row, col);
    int n = _dimension;
    
    // top
    if(i>0 && _states[i-1][j])
      _connections.union(x, x-n);    
    
    // right
    if(j<n-1 && _states[i][j+1])
      _connections.union(x, x+1);
    
    // bottom
    if(i<n-1 && _states[i+1][j])    
      _connections.union(x, x+n);
    
    // left
    if(j>0 && _states[i][j-1])    
      _connections.union(x, x-1);  
  }
  
  // is site (row, col) open?
  public boolean isOpen(int row, int col)
  {
    if (row < 1 || col < 1 || row > _dimension || col > _dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    return _states[row-1][col-1];
  }
  
  // is site (row, col) full?
  public boolean isFull(int row, int col)
  {
    if (row < 1 || col < 1 || row > _dimension || col > _dimension)
      throw new java.lang.IndexOutOfBoundsException();
    
    return _connections.connected(0, getSite(row, col));
  }
  
  // number of open sites
  public int numberOfOpenSites()
  {
    return _openSites;
  }
   
  // does the system percolate?
  public boolean percolates()
  {
    return _connections.connected(0, _lastSite);
  }
  
//  public static void main(String[] args)
//  {
//    Percolation p = new Percolation(5);
//    p.open(1,1);
//    p.open(2,1);
//    p.open(3,1);
//    p.open(4,1);
//    p.open(5,1);
//    StdOut.println(p.percolates());    
//  }
}