import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast 
{
    private final WordNet W;
    
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet)         
    {
        W = wordnet;
    }
    
    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)   
    {
        int max = 0;
        String result = null;
        for(String x : nouns)
        {
            int sum = 0;            
            for(String y : nouns)
            {
                sum += W.distance(x, y);
            }
            
            if (max < sum)
            {
                max = sum;
                result = x;
            }
        }
        
        return result;   
    }
    
    // see test client below
    public static void main(String[] args)  
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) 
        {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}