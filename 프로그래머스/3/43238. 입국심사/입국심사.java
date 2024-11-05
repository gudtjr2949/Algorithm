import java.util.*;

class Solution {
    
    static int N;
    static long answer;
    
    public long solution(int n, int[] times) {
                
        solve(n, times);
        
        return answer;
    }
    
    static void solve(int n, int[] times) {
        long left = 0;
        long right = Long.MAX_VALUE;
        
        while (left < right) {
            long mid = (left + right) / 2;
            
            if (count(mid, times, n) < n) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid;
            }
        }
    }
    
    static long count(long mid, int[] times, int n) {
        long result = 0;
        
        for (int i = 0 ; i < times.length ; i++) {
            result += mid / times[i];
            
            if (result > n) return n+1;
        }
        
        return result;
    }
  
}