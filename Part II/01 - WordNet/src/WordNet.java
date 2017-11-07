import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet 
{
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
        while (line != null)
        {
            arr = line.split("\\,");          
            vertices.add(arr[1]);
            arr = arr[1].split("\\ ");            
            for (String noun : arr)
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
        
        digraph = new Digraph(index);
                
        file = new In(hypernyms);
        line = file.readLine();
        while (line != null)
        {
            arr = line.split("\\,");
            for (int i = 1; i < arr.length; i++)
            {
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
        
        ArrayList<Integer> v = words.get(nounA);
        ArrayList<Integer> w = words.get(nounB);
        
        SAP sap = new SAP(digraph);
        
        return sap.length(v, w);
    }
    
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        ArrayList<Integer> v = words.get(nounA);
        ArrayList<Integer> w = words.get(nounB);
        
        SAP sap = new SAP(digraph);
        int index = sap.ancestor(v, w);
        
        return vertices.get(index);
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
          WordNet wn = new WordNet(args[0], args[1]);
          for (String i : wn.nouns())
          {
              StdOut.println(i);
          }
    }
}