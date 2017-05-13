import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
  private Item[] arr;
  private int size;
  
  public RandomizedQueue()
  {
     arr = (Item[])new Object[4];
  }
  
  public boolean isEmpty() { return size == 0; }
  public int size() { return size; }
  
  private void resize(int capacity)
  {
    Item[] copy = (Item[])new Object[capacity];
    for (int i = 0; i < size; i++)
    {
      copy[i] = arr[i];
    }
    arr = copy;
  }
  
  public void enqueue(Item item)
  {
    if (item == null)
      throw new java.lang.NullPointerException();
    
    if (arr.length == size)
    {
      resize(2*arr.length);
    }
    
    arr[size++] = item;
  }
  
  public Item dequeue()
  {
    if (size == 0)
      throw new java.util.NoSuchElementException();
    
    if (arr.length == 4*size)
    {
      resize(arr.length/2);
    }
    
    int i = StdRandom.uniform(0, size);
    Item item = arr[i];
    arr[i] = arr[--size];
    arr[size] = null;
    return item;
  }
  
  public Item sample()
  {
    if (size == 0)
      throw new java.util.NoSuchElementException();
    
    return arr[StdRandom.uniform(0, size)];
  }
  
  public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }
  
  private class RandomizedQueueIterator implements Iterator<Item>
  {
    private Item[] items;
    private int current;
    
    public RandomizedQueueIterator()
    {
      items = (Item[])new Object[size];
      for (int i = 0; i < size; i++)
      {
        items[i] = arr[i];
      }
      
      StdRandom.shuffle(items);
    }
    
    public boolean hasNext() { return current < items.length; }
    public void remove() { throw new java.lang.UnsupportedOperationException(); }
    
    public Item next()
    {
      if (current == items.length)      
        throw new java.util.NoSuchElementException();
      
      return items[current++];
    }
  }
  
  public static void main(String[] args)
  {
    RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);
    StdOut.print(q.dequeue());
    StdOut.print(q.dequeue());
    StdOut.print(q.dequeue());
    StdOut.print(q.dequeue());
    StdOut.print(q.dequeue());
  }
}