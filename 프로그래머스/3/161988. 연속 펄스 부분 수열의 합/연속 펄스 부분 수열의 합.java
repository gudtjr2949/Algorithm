import java.util.*;

class Solution {
    
    static long answer;
    static int[][] sequences;
    static long[][] dp;
    
    public long solution(int[] sequence) {
        sequences = new int[2][sequence.length];
        for (int i = 0 ; i < sequence.length ; i++) {
            if (i % 2 == 0) {
                sequences[0][i] = sequence[i] * 1;
                sequences[1][i] = sequence[i] * -1;
            } else {
                sequences[0][i] = sequence[i] * -1;
                sequences[1][i] = sequence[i] * 1;
            }
        }
        
        dp = new long[2][sequence.length];
        for (int i = 0 ; i < 2 ; i++) 
            Arrays.fill(dp[i], Long.MIN_VALUE);
        
        solve();
        
        return answer;
    }
    
    static void solve() {
        dp[0][0] = sequences[0][0];
        dp[1][0] = sequences[1][0];
        
        for (int i = 1 ; i < sequences[0].length ; i++) {
            dp[0][i] = Math.max(dp[0][i-1] + sequences[0][i], sequences[0][i]);
            dp[1][i] = Math.max(dp[1][i-1] + sequences[1][i], sequences[1][i]);
        }
        
        for (int i = 0 ; i < sequences[0].length ; i++) {
            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        }
    }
}