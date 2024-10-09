import java.util.*;

class Solution {
    
    static int answer = 0;
    
    public int solution(int[] cookie) {
        
        solve(cookie);
        
        return answer;
    }
    
    static void solve(int[] cookie) {
        int N = cookie.length;
        
        for (int i = 0 ; i < N-1 ; i++) {
            int left = i;
            int right = i+1;
            int lSum = cookie[left];
            int rSum = cookie[right];
            
            while (true) {
                if (lSum == rSum && answer < lSum) {
                    answer = lSum;
                } else if (lSum >= rSum && right+1 < N) {
                    rSum += cookie[++right];
                } else if (rSum >= lSum && left-1 >= 0) {
                    lSum += cookie[--left];
                } else break;
            }
        }
    }
}