import java.util.*;

class Solution {
    
    static int N, M;
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        init(m, n);
        setDP(puddles);
        solve();
        return dp[N][M];
    }
    
    static void solve() {
        dp[1][1] = 1;
        
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                if (i == 1 && j == 1) continue;
                
                if (dp[i][j] == -1) dp[i][j] = 0;
                else dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1_000_000_007;
            }
        }
    }
    
    static void setDP(int[][] puddles) {
        for (int[] arr : puddles) {
            dp[arr[1]][arr[0]] = -1;
        }
    }
    
    static void init(int m, int n) {
        N = n;
        M = m;
        dp = new int[N+1][M+1];
    }
}