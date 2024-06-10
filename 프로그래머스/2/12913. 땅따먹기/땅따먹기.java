class Solution {
    
    static int[][] dp;
    
    int solution(int[][] land) {
        int answer = 0;
        dp = new int[land.length][4];
        
        for (int i = 0 ; i < land.length ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                dp[i][j] = land[i][j];
            }
        }
        
        solve();
        
        for (int i = 0 ; i < 4 ; i++) {
            answer = Math.max(answer, dp[dp.length-1][i]);
        }
        
        return answer;
    }
    
    static void solve() {
        for (int i = 1 ; i < dp.length ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                int max = 0;
                for (int k = 0 ; k < 4 ; k++) {
                    if (j == k) continue;
                    max = Math.max(max, dp[i][j] + dp[i-1][k]);
                }
                dp[i][j] = max;
            }
        }
    }
}