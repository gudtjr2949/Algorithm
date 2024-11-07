class Solution {
    
    static int answer;
    static int[][] dp;
    
    public int solution(int n, int[][] results) {
        dp = new int[n+1][n+1];
        
        for (int i = 0 ; i < results.length ; i++) {
            dp[results[i][0]][results[i][1]] = 1;
            dp[results[i][1]][results[i][0]] = -1;
        }
        
        solve(n);
        
        return answer;
    }
    
    static void solve(int n) {
        for (int k = 1 ; k <= n ; k++) {
            for (int i = 1 ; i <= n ; i++) {
                for (int j = 1 ; j <= n ; j++) {
                    if (i != j && dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                        dp[j][i] = -1;
                        dp[k][i] = -1;
                        dp[j][k] = -1;
                    }
                }
            }
        }
        
        for (int i = 1 ; i <= n ; i++) {
            int know = 0;
            for (int j = 1 ; j <= n ; j++) {
                if (dp[i][j] == 1 || dp[i][j] == -1) know++;
            }
            
            if (know == n-1) answer++;
        }
    }
}