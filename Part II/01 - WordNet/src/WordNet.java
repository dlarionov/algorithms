import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet 
{
    private Digraph graph;
    private ArrayList<String> vertices;   
    private HashMap<String, String> words;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        if (synsets == null || hypernyms == null)
            throw new java.lang.IllegalArgumentException();
        
        vertices = new ArrayList<String>();
        words = new HashMap<String, String>();
        
        In file = new In(synsets);
        String line = file.readLine();
        String[] arr;
        int index = 0;
        while(line != null)
        {
            arr = line.split("\\,");          
            vertices.add(arr[1]);
            arr = arr[1].split("\\ ");            
            for(String noun : arr)
            {
                if (words.containsKey(noun))
                {
                    String ids = words.get(noun);
                    ids += "," + Integer.toString(index);
                    words.put(noun, ids);
                }
                else
                {
                    words.put(noun, Integer.toString(index));
                }
            }            
            line = file.readLine();
            index++;
        }
        
        graph = new Digraph(vertices.size());
        
        file = new In(hypernyms);
        line = file.readLine();
        while(line != null)
        {
            arr = line.split("\\,");
            for(int i = 1; i < arr.length; i++)
            {
                graph.addEdge(Integer.parseInt(arr[i]), Integer.parseInt(arr[0]));                
            }                
            line = file.readLine();
        }
        
        Topological dag = new Topological(graph);
        if (!dag.hasOrder())
        {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return words.keySet();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        if (word == null)
            throw new java.lang.IllegalArgumentException();
        
        return words.containsKey(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        String[] a = words.get(nounA).split("\\,");
        String[] b = words.get(nounA).split("\\,");
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < a.length; i++)
        {
            for(int j = 0; j < b.length; j++)
            {
                min = Math.min(min, distance(i, j));
            }
        }       
        
        return min;
    }
    
    private int hypernym(int x)
    {
        for(int i : graph.adj(x))
        {
            return i;
        }
        return -1;
    }
    
    private Queue<Integer> path(int x)
    {
        Queue<Integer> q = new Queue<Integer>();
        while(x > 0)
        {
            q.enqueue(x);
            x = hypernym(x);
        }
        return q;
    }
    
    private int distance(int v, int w)
    {
        if (v == w)
            return 0;
        
        Queue<Integer> pathV = path(v);
        Queue<Integer> pathW = path(w);
        
        while(pathV.size() > 0 && pathW.size() > 0)
        {
            if (pathV.dequeue() != pathW.dequeue())
                break;                      
        }      
       
        return pathV.size() + pathW.size() + 2;
    }
    
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        return null;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet wn = new WordNet("../test/synsets500-subgraph.txt", "../test/hypernyms100-subgraph.txt");
    }
}