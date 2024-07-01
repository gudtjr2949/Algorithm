class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for (int i = 0 ; i < money.length ; i++) {
            int cur = money[i];
            for (int j = cur ; j <= n ; j++) {
                dp[j] += dp[j-cur];
            }
        }
        
        answer = dp[n];
        
        return answer;
    }
}