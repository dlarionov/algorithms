import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation 
{
  public static void main(String[] args)
  {
    RandomizedQueue<String> q = new RandomizedQueue<String>();
    
    int k = StdIn.readInt();
    while (!StdIn.isEmpty()) 
    {
      q.enqueue(StdIn.readString());
    }
    
    for(int i = 0; i < k; i++)
    {
      StdOut.println(q.dequeue());
    }    
  }
}