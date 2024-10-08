class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] map = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        
        for (int i = 0 ; i < puddles.length ; i++) {
            map[puddles[i][1]][puddles[i][0]] = 1;
        }
        
        dp[1][1] = 1;
        
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                if (i == 1 && j == 1) continue;
                
                if (map[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
            }
        }
        
        return dp[n][m];
    }
}