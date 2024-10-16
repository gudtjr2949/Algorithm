class Solution {
    
    static int answer, N;
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        answer = 0;
        N = triangle.length;
        dp = new int[N][N];
        
        solve(triangle);
        
        answer = dp[0][0] + triangle[0][0];
        
        return answer;
    }
    
    static void solve (int[][] triangle) {
        for (int i = N-1 ; i >= 1 ; i--) {
            for (int j = 0 ; j < triangle[i].length ; j++) {
                if (dp[i-1][j] < dp[i][j] + triangle[i][j]) {
                    dp[i-1][j] = dp[i][j] + triangle[i][j];
                }
                
                if (j-1 >= 0 && dp[i-1][j-1] < dp[i][j] + triangle[i][j]) {
                    dp[i-1][j-1] = dp[i][j] + triangle[i][j];
                }
            }
        }
    }
}