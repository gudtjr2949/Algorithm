import java.util.*;

class Solution {
    
    public int solution(int[] money) {
        int answer = 0;
        int N = money.length;
        int[] dp1 = new int[N];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        
        for (int i = 2 ; i < N-1 ; i++) {
            dp1[i] = Math.max(money[i] + dp1[i-2], dp1[i-1]);
            answer = Math.max(answer, dp1[i]);
        }
        
        int[] dp2 = new int[N];
        dp2[0] = 0;
        dp2[1] = money[1];
        
        for (int i = 2 ; i < N ; i++) {
            dp2[i] = Math.max(money[i] + dp2[i-2], dp2[i-1]);
            answer = Math.max(answer, dp2[i]);
        }
        
        return answer;
    }
}