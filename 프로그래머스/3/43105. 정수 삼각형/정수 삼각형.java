class Solution {
    
    static int answer, N;
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        answer = 0;
        N = triangle.length;
        dp = new int[N][N];
        
        solve(triangle);
        
        answer = dp[0][0];
        
        return answer;
    }
    
    static void solve(int[][] triangle) {
        for (int i = 0 ; i < triangle[N-1].length ; i++)
            dp[N-1][i] = triangle[N-1][i];
        
        for (int i = N-2 ; i >= 0 ; i--) {
            for (int j = 0 ; j < triangle[i].length ; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }
    }
    
}