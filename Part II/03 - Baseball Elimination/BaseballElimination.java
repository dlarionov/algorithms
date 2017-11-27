import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.Bag;
// import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

public class BaseballElimination
{
    private final HashMap<String, Integer> map;
    private final String[] names;
    private final int[] w;
    private final int[] ls;
    private final int[] r;
    private final int[][] g;
    private final int len;
    
    public BaseballElimination(String filename) {
        In file = new In(filename);
        String str = file.readLine();
        len = Integer.parseInt(str);
        
        names = new String[len];
        w = new int[len];
        ls = new int[len];
        r = new int[len];
        g = new int[len][len];
        
        String[] bufer;
        map = new HashMap<String, Integer>();
        for (int i = 0; i < len; i++) {
            str = file.readLine();
            bufer = str.trim().split("\\s+");
            
            map.put(bufer[0], i);
            names[i] = bufer[0];
            w[i] = Integer.parseInt(bufer[1]);
            ls[i] = Integer.parseInt(bufer[2]);
            r[i] = Integer.parseInt(bufer[3]);            
            
            for (int j = 0; j < len; j++) {
                g[i][j] = Integer.parseInt(bufer[j+4]);
            }
        }
    }
    
    // number of teams
    public int numberOfTeams() {
        return len;
    }
    
    // all teams
    public Iterable<String> teams() {
        return map.keySet();
    }
    
    // number of wins for given team
    public int wins(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return w[map.get(team)];
    }
    
    // number of losses for given team
    public int losses(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return ls[map.get(team)];
    }
    
    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        return r[map.get(team)];
    }
    
    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (team1 == null || team2 == null || !map.containsKey(team1) || !map.containsKey(team2))
            throw new java.lang.IllegalArgumentException();
        return g[map.get(team1)][map.get(team2)];
    }
    
    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        int id = map.get(team);
        int winner = findSimpleWin(id);
        if (winner > -1)
            return true;
        
        FlowNetwork nw = createFlowNetwork(id);
        new FordFulkerson(nw, 0, nw.V()-1); // todo ugly code        
        
        for (FlowEdge e : nw.adj(0)) {
            if (e.capacity() > e.flow())
                return true;
        }        
        return false;
    }
    
    private int findSimpleWin(int id) {
        for (int i = 0; i < len; i++) {
            if (i == id)
                continue;         
            if (w[id] + r[id] - w[i] < 0)
                return i;
        }
        return -1;
    }
    
    private FlowNetwork createFlowNetwork(int id) {
        int n = len - 1;
        // s + nubner of games without eliminated team + number of teams + t
        int v = 1 + (n * n - n) / 2 + len + 1; 
        
        FlowNetwork nw = new FlowNetwork(v);
        int s = 0;
        int t = v - 1;        
        int idx = 1;
        for (int i = 0; i < len; i++) {
            if (i == id)
                continue;
            
            for (int j = i + 1; j < len; j++) {
                if (j == id)
                    continue;                
                nw.addEdge(new FlowEdge(s, idx, g[i][j]));
                nw.addEdge(new FlowEdge(idx, t - i - 1, Double.POSITIVE_INFINITY));
                nw.addEdge(new FlowEdge(idx, t - j - 1, Double.POSITIVE_INFINITY));                
                idx++;
            }
            
            nw.addEdge(new FlowEdge(t - i - 1, t, w[id] + r[id] - w[i]));
        }        
        return nw;
    }
    
    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (team == null || !map.containsKey(team))
            throw new java.lang.IllegalArgumentException();
        
        Bag<String> cut = new Bag<String>();
        
        int id = map.get(team);
        int winner = findSimpleWin(id);
        if (winner > -1) {
            cut.add(names[winner]);
            return cut;
        }
        
        FlowNetwork nw = createFlowNetwork(id);
        FordFulkerson ff = new FordFulkerson(nw, 0, nw.V()-1);
        
        for (int i = 0; i < len; i++) {
            if (ff.inCut(nw.V()-1 - i - 1))
                cut.add(names[i]);
        } 
        return cut.size() > 0 ? cut : null;
    }
}