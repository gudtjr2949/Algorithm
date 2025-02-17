import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[][] dp;
    static List<List<Integer>> adj;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        init(n);
        setAdj(lighthouse);
        visited[1] = true;
        dfs(1);
        answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }
    
    static void dfs(int idx) {
        dp[idx][0] = 0;
        dp[idx][1] = 1;
        
        for (int child : adj.get(idx)) {
            if (!visited[child]) {
                visited[child] = true;
                dfs(child);
                
                dp[idx][0] += dp[child][1];
                dp[idx][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
    
    static void setAdj(int[][] lighthouse) {
        for (int i = 0 ; i < lighthouse.length ; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
    }
    
    static void init(int n) {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[n+1];
        dp = new int[n+1][2];
    }
}