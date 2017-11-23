import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class BaseballElimination
{
    private final HashMap<String, Integer> teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] schedule;
    
    public BaseballElimination(String filename) {
        In file = new In(filename);
        String str = file.readLine();
        int len = Integer.parseInt(str);
        
        wins = new int[len];
        losses = new int[len];
        remaining = new int[len];
        schedule = new int[len][len];
        
        String[] bufer;
        teams = new HashMap<String, Integer>();
        for (int i = 0; i < len; i++) {
            str = file.readLine();
            bufer = str.trim().split("\\s+");
            
            teams.put(bufer[0], i);
            
            wins[i] = Integer.parseInt(bufer[1]);
            losses[i] = Integer.parseInt(bufer[2]);
            remaining[i] = Integer.parseInt(bufer[3]);            
            
            for (int j = 0; j < len; j++) {
                schedule[i][j] = Integer.parseInt(bufer[j+4]);
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
        return wins[teams.get(team)];
    }
    
    // number of losses for given team
    public int losses(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return losses[teams.get(team)];
    }
    
    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !teams.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return remaining[teams.get(team)];
    }
    
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (team1 == null || team2 == null || !teams.containsKey(team1) || !teams.containsKey(team2))
            throw new java.lang.IllegalArgumentException();
        return schedule[teams.get(team1)][teams.get(team2)];
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