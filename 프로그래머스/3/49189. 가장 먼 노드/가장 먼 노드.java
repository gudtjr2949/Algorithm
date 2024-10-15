import java.util.*;

class Solution {
    
    static int answer;
    static int[] dp;
    static List<List<Integer>> adj;
    
    public int solution(int n, int[][] edge) {
        answer = 0;
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) 
            adj.add(new ArrayList<>());
        
        for (int i = 0 ; i < edge.length ; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        
        dp = new int[n+1];
        
        dijk(n);
        
        return answer;
    }
    
    static void dijk(int n) {
        Arrays.fill(dp, 100_000_000);
        
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        dp[1] = 0;
        int max = 0;
        
        while (!Q.isEmpty()) {
            Integer now = Q.poll();
            
            for (Integer next : adj.get(now)) {
                if (dp[next] > dp[now] + 1) {
                    dp[next] = dp[now] + 1;
                    max = Math.max(max, dp[next]);
                    Q.add(next);
                }
            }
        }
        
        for (int i = 1 ; i <= n ; i++) {
            if (dp[i] == max) {
                answer++;
            }
        }
    }
}