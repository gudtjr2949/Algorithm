class Solution {
    
    static int max;
    static int[][] dp;
        
    public int solution(int[][] board) {
        int answer = 0;
        
        dp = new int[board.length+1][board[0].length+1];
        
        solve(board);      
        
        answer = max * max;
        
        return answer;
    }
    
    static void solve(int[][] board) {
        for (int i = 1 ; i <= board.length ; i++) {
            for (int j = 1 ; j <= board[0].length ; j++) {
                if (board[i-1][j-1] == 0) continue;
                
                int num1 = dp[i-1][j];
                int num2 = dp[i][j-1];
                int num3 = dp[i-1][j-1];
                
                int min = Math.min(num1, Math.min(num2, num3));
                
                dp[i][j] = min+1;
                
                max = Math.max(max, dp[i][j]);
            }
        }
    }
}