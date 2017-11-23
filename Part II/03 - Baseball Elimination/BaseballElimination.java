import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class BaseballElimination
{
    private final HashMap<String, Stat> map;
    
    public BaseballElimination(String filename) {
        
        map = new HashMap<String, Stat>();
    }
    
    private class Stat
    {
        public int wins;
        public int losses;
        public int remaining;
        
        public Stat(int w, int l, int r) {
            wins = w;
            losses = l;
            remaining = r;
        }
    }
    
    // number of teams
    public int numberOfTeams() {
        return map.size();
    }
    
    // all teams
    public Iterable<String> teams() {
        return map.keySet();
    }
    
    // number of wins for given team
    public int wins(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        return map.get(team).wins;
    }
    
    // number of losses for given team
    public int losses(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        return map.get(team).losses; 
    }
    
    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        return map.get(team).remaining;
    }
    
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return 0;
    }
    
    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }
    
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }
}