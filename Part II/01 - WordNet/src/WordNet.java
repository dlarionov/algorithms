import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet 
{
    private Digraph graph;
    private ArrayList<String> vertices;   
    
    // key is noun, value is synset ids devided by comma
    private HashMap<String, String> map;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        if (synsets == null || hypernyms == null)
            throw new java.lang.IllegalArgumentException();
        
        vertices = new ArrayList<String>();
        map = new HashMap<String, String>();
        
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
                if (map.containsKey(noun))
                {
                    String ids = map.get(noun);
                    ids += "," + Integer.toString(index);
                    map.put(noun, ids);
                    
                    // StdOut.println(noun + " " + ids);
                }
                else
                {
                    map.put(noun, Integer.toString(index));
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
        
        // StdOut.println(graph);
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return map.keySet();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        if (word == null)
            throw new java.lang.IllegalArgumentException();
        
        return map.containsKey(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        
        return 0;
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