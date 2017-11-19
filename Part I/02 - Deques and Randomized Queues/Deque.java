import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> 
{
  private Node first;
  private Node last;
  private int size;
  
  private class Node
  {
    private Item item;
    private Node next;
    private Node prev;
  }
  
  public Deque() { }
  
  public boolean isEmpty() { return first == null; }
  
  public int size() { return size; }
  
  public void addFirst(Item item)
  {
    if (item == null)    
      throw new java.lang.NullPointerException();    
    
    Node node = new Node();
    node.item = item;
    
    if (first == null)
    {
      first = node;
      last = node;
    }
    else
    {
      first.prev = node;
      node.next = first;
      first = node;
    }    
    size++;    
  }
  
  public void addLast(Item item)
  {
    if (item == null)    
      throw new java.lang.NullPointerException();    
    
    Node node = new Node();
    node.item = item;
    
    if (first == null)
    {
      first = node;
      last = node;
    }
    else
    {
      node.prev = last;
      last.next = node;
      last = node;
    }    
    size++;    
  }
  
  public Item removeFirst()
  {
    if (first == null)    
      throw new java.util.NoSuchElementException();
        
    Node node = first;
    if (first.next == null)
    {
      first = null;
      last = null;
    }
    else
    {
      first = first.next;
      first.prev = null;
    }   
    
    size--;    
    return node.item;
  }
  
  public Item removeLast()
  {
    if (first == null)
      throw new java.util.NoSuchElementException();    
    
    Node node = last;
    if (first.next == null)
    {
      first = null;
      last = null;
    }
    else
    {
      last = last.prev;
      last.next = null;
    }   
    
    size--;    
    return node.item;
  }

  public Iterator<Item> iterator() { return new DequeIterator(); }
  
  private class DequeIterator implements Iterator<Item>
  {
    private Node current = first;    
    public boolean hasNext() { return current != null; }    
    public void remove() { throw new java.lang.UnsupportedOperationException(); }
    
    public Item next()
    {
      if (current == null)      
        throw new java.util.NoSuchElementException();
      
      Item item = current.item;               
      current = current.next;
      return item;
    }
  }
  
  public static void main(String[] args)
  {
    Deque<Integer> deck = new Deque<Integer>();
    deck.addFirst(1);
    for (int i : deck)
    {
      StdOut.print(i);
    }
  }
}