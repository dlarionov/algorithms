import edu.princeton.cs.algs4.StdOut;
public class Test {
    public static void main(String[] args) {
        BaseballElimination x = new BaseballElimination(args[0]);
        StdOut.println(x.isEliminated(args[1]));        
        for (String i : x.certificateOfElimination(args[1])) {
            StdOut.println(i);
        }
    }    
}