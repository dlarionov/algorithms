import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class BaseballElimination
{
    private final HashMap<String, Integer> teams;
    private final int[] w;
    private final int[] l;
    private final int[] r;
    private final int[][] s;
    
    public BaseballElimination(String filename) {
        In file = new In(filename);
        String str = file.readLine();
        int len = Integer.parseInt(str);
        
        w = new int[len];
        l = new int[len];
        r = new int[len];
        s = new int[len][len];
        
        String[] bufer;
        teams = new HashMap<String, Integer>();
        for (int i = 0; i < len; i++) {
            str = file.readLine();
            bufer = str.trim().split("\\s+");
            
            teams.put(bufer[0], i);
            
            w[i] = Integer.parseInt(bufer[1]);
            l[i] = Integer.parseInt(bufer[2]);
            r[i] = Integer.parseInt(bufer[3]);            
            
            for (int j = 0; j < len; j++) {
                s[i][j] = Integer.parseInt(bufer[j+4]);
            }
            
            // StdOut.println("name: " + bufer[0] + " w: " + bufer[1] + " l: " + bufer[2] + " r: " + bufer[3]);
        }
    }
    
    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }
    
    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }
    
    // number of wins for given team
    public int wins(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return w[teams.get(team)];
    }
    
    // number of losses for given team
    public int losses(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return l[teams.get(team)];
    }
    
    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return r[teams.get(team)];
    }
    
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (team1 == null || team2 == null || !teams.containsKey(team1) || !teams.containsKey(team2))
            throw new java.lang.IllegalArgumentException();
        return s[teams.get(team1)][teams.get(team2)];
    }
    
    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        int idx = teams.get(team);
        int games = w[idx] + r[idx];
        
        int v = teams.size();
        v = (v * v - v) / 2 + 2;        
        FlowNetwork nw = new FlowNetwork(v);
        
        int s = 0;
        int t = v - 1;
        
        int x = t;
        for (int i : teams.values()) {
            if (i == idx) 
                continue;
            
            int f = games - w[i];
            if (f < 0)
                return true; // simple elumination
            
            nw.addEdge(new FlowEdge(--x, t, f));
        }
        
        return false;
    }
    
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }
}