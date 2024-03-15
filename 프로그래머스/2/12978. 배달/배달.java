class Solution {
    
    static int MAX = 500_000;
    static int[][] dp;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        dp = new int[N+1][N+1];
        
        for (int i = 0 ; i <= N ; i++) {
            for (int j = 0 ; j <= N ; j++)
                if (i != j) dp[i][j] = MAX;
        }

        for (int i = 0 ; i < road.length ; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            dp[a][b] = Math.min(dp[a][b], c);
            dp[b][a] = Math.min(dp[b][a], c);
        }
        
        solve(N);

        for (int i = 1 ; i <= N ; i++) {
            if (dp[1][i] <= K) answer++;
        }

        return answer;
    }
    
    static void solve(int N) {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i != j) dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }
}