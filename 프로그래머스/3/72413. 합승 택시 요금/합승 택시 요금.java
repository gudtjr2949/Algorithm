import java.util.*;

class Solution {
    
    static int answer, MAX = 100_000_000;
    static int[][] dp;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dp = new int[n+1][n+1];
        
        for (int i = 0 ; i <= n ; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }
        
        for (int i = 0 ; i < fares.length ; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            dp[from][to] = cost;
            dp[to][from] = cost;
        }
        
        solve(n, s, a, b);
        
        return answer;
    }
    
    static void solve(int n, int s, int a, int b) {
        for (int k = 1 ; k <= n ; k++) {
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1 ; j <= n ; j++) {
                    if (i != j && dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        
        answer = dp[s][a] + dp[s][b];
        
         for (int i = 1 ; i <= n ; i++) 
            answer = Math.min(answer, dp[s][i] + dp[i][a] + dp[i][b]);
    }
}