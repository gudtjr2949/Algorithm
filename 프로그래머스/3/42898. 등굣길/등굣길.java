import java.util.*;

class Solution {
    
    static int answer;
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n+1][m+1];
        
        for (int i = 0 ; i < puddles.length ; i++) 
            dp[puddles[i][1]][puddles[i][0]] = -1;
        
        solve(m, n);
    
        return answer;
    }
    
    static void solve(int m, int n) {
        dp[1][1] = 1;
        
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                if (i == 1 && j == 1) continue;
                
                if (dp[i][j] == -1) dp[i][j] = 0;
                else dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1_000_000_007;
            }
        }
        
        answer = dp[n][m];
    }
}