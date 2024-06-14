class Solution {
    
    static long answer;
    
    public long solution(int n) {
        answer = 0;
        solve(n);
        return answer;
    }
    
    static void solve(int n) {
       int[] dp = new int[n+2];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3 ; i <= n ; i++) {
            dp [i] = (dp[i-1] + dp[i-2]) % 1234567;
        }
        
        answer = dp[n];
    }
}