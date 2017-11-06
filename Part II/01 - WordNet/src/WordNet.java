import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet 
{
    private final Graph graph;
    private final Digraph digraph;
    private final ArrayList<String> vertices = new ArrayList<String>();   
    private final HashMap<String, ArrayList<Integer>> words = new HashMap<String, ArrayList<Integer>>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        if (synsets == null || hypernyms == null)
            throw new java.lang.IllegalArgumentException();
        
        In file = new In(synsets);
        String line = file.readLine();
        String[] arr;
        ArrayList<Integer> ids;
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
                    ids = words.get(noun);
                    ids.add(index);
                    // words.put(noun, ids);
                }
                else
                {
                    ids = new ArrayList<Integer>();
                    ids.add(index);
                    words.put(noun, ids);
                }                
            }            
            line = file.readLine();
            index++;
        }
        
        graph = new Graph(index);
        digraph = new Digraph(index);
                
        file = new In(hypernyms);
        line = file.readLine();
        while(line != null)
        {
            arr = line.split("\\,");
            for(int i = 1; i < arr.length; i++)
            {
                graph.addEdge(Integer.parseInt(arr[i]), Integer.parseInt(arr[0]));
                digraph.addEdge(Integer.parseInt(arr[i]), Integer.parseInt(arr[0]));
            }                
            line = file.readLine();
        }
        
        Topological dag = new Topological(digraph);
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
        
        ArrayList<Integer> idsV = words.get(nounA);
        ArrayList<Integer> idsW = words.get(nounB);
        
        int min = Integer.MAX_VALUE;
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, idsW);
        for(int i = 0; i < idsV.size(); i++)
        {
            min = Math.min(min, paths.distTo(idsV.get(i)));
        }
        
        return min;
    }
    
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        ArrayList<Integer> idsV = words.get(nounA);
        ArrayList<Integer> idsW = words.get(nounB);
        
        int dist;
        int min = Integer.MAX_VALUE;
        Iterable<Integer> path = null;        
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, idsW);
        for(int i = 0; i < idsV.size(); i++)
        {
            dist = paths.distTo(idsV.get(i));
            if (min > dist)
            {
                min = dist;
                path = paths.pathTo(i);
            }
        }
        
        // todo use binary search for finding root in the path
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i : path)
        {
            arr.add(i);
        }    
        
        int index = arr.size() - 1;
        while(index > 0 && contains(digraph.adj(arr.get(index)), arr.get(index-1)))
        {
            index--;
        }
        
        return vertices.get(index);
    }
    
    private static Boolean contains(Iterable<Integer> data, int x)
    {
        for (int i : data) 
        {
            if (x == i) 
            {
                return true;
            }
        }
        return false;
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet wn = new WordNet("../test/synsets500-subgraph.txt", "../test/hypernyms100-subgraph.txt");    
    }
}