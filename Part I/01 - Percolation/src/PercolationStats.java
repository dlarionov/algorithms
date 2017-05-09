import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.StdIn;

public class PercolationStats 
{
  private double[] _res;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials)
  {
    if (n < 1 || trials < 1)
      throw new java.lang.IllegalArgumentException();
    
    _res = new double[trials];
    double numberOfSites = n*n;
    for(int i = 0; i < trials; i++)
    {
      Percolation p = new Percolation(n);
      while(!p.percolates())
      {
        int row = StdRandom.uniform(1, n+1);
        int col = StdRandom.uniform(1, n+1);
        if (!p.isOpen(row, col))
          p.open(row, col);        
      }
      _res[i] = p.numberOfOpenSites() / numberOfSites;
    }
  }
  
  // sample mean of percolation threshold
  public double mean()
  {
    return StdStats.mean(_res);
  }
  
  // sample standard deviation of percolation threshold
  public double stddev()
  {
    return StdStats.stddev(_res);
  }
  
  // low  endpoint of 95% confidence interval
  public double confidenceLo()
  {
    return StdStats.mean(_res) - 1.96 * StdStats.stddev(_res) / Math.sqrt(_res.length);
  }
  
  // high endpoint of 95% confidence interval
  public double confidenceHi()
  {
    return StdStats.mean(_res) - 1.96 * StdStats.stddev(_res) / Math.sqrt(_res.length);
  }
  
  public static void main(String[] args)
  {
    PercolationStats ps=new PercolationStats(300,1000); 
    StdOut.print("mean = "+ps.mean()+"\n");
    StdOut.print("std dev = "+ps.stddev()+"\n");
    StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
  } 
}