import java.util.*;

class Solution {
    
    static int answer, MAX = 500_000;
    static int[][] dp;
    
    public int solution(int N, int[][] road, int K) {
        dp = new int[N+1][N+1];
        for (int i = 0 ; i <= N ; i++) 
            Arrays.fill(dp[i], MAX);
        
        for (int i = 0 ; i <= N ; i++) 
            dp[i][i] = 0;
        
        for (int i = 0 ; i < road.length ; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            dp[a][b] = Math.min(dp[a][b], c);
            dp[b][a] = Math.min(dp[b][a], c);
        }
        
        solve(N, K);

        return answer;
    }
    
    static void solve(int N, int K) {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i != j && dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        
        for (int i = 1 ; i <= N ; i++) {
            if (dp[1][i] <= K) answer++;
        }
    }

}