import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.Bag;
import java.util.ArrayList;
import java.util.HashMap;

public class WordNet 
{
    private final ArrayList<String> vertices;   
    private final HashMap<String, Bag<Integer>> words;
    private final SAP sap;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        if (synsets == null || hypernyms == null)
            throw new java.lang.IllegalArgumentException();
        
        int idx = 0;
        String[] arr;
        vertices = new ArrayList<String>();
        words = new HashMap<String, Bag<Integer>>();
        
        In file = new In(synsets);
        String line = file.readLine();
        while (line != null) {
            arr = line.split("\\,");
            vertices.add(arr[1]);
            arr = arr[1].split("\\ ");
            for (String noun : arr) {
                if (words.containsKey(noun)) {
                    Bag<Integer> ids = words.get(noun);
                    ids.add(idx);
                } else {
                    Bag<Integer> ids = new Bag<Integer>();
                    ids.add(idx);
                    words.put(noun, ids);
                }
            }            
            line = file.readLine();
            idx++;
        }
        
        Digraph digraph = new Digraph(idx);
        
        file = new In(hypernyms);
        line = file.readLine();
        while (line != null) {
            arr = line.split("\\,");
            for (int i = 1; i < arr.length; i++)
                digraph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i]));            
            line = file.readLine();
        }
        
        int cc = 0;
        for (int v = 0; v < digraph.V(); v++) {
            if (digraph.outdegree(v) == 0) 
                cc++;
        }
        if (cc > 1)
            throw new java.lang.IllegalArgumentException();
        
        Topological dag = new Topological(digraph);
        if (!dag.hasOrder())
            throw new java.lang.IllegalArgumentException();        
        
        sap = new SAP(digraph);        
    }
    
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return words.keySet();
    }
    
    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new java.lang.IllegalArgumentException();
        
        return words.containsKey(word);
    }
    
    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        Bag<Integer> v = words.get(nounA);
        Bag<Integer> w = words.get(nounB);        
        
        return sap.length(v, w);
    }
    
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounA))
            throw new java.lang.IllegalArgumentException();
        
        Bag<Integer> v = words.get(nounA);
        Bag<Integer> w = words.get(nounB);
        
        int index = sap.ancestor(v, w);        
        return vertices.get(index);
    }
    
    // do unit testing of this class
    public static void main(String[] args)
    {
        WordNet wn = new WordNet(args[0], args[1]);
        StdOut.println(wn.nouns());
    }
}