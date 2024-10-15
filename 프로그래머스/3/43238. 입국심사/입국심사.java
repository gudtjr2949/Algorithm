import java.util.*;

class Solution {
    
    static int N;
    static long answer;
    
    public long solution(int n, int[] times) {
        answer = 0;
        
        N = n;
        solve(times);
        
        return answer;
    }
    
    static void solve(int[] times) {
        long left = 0;
        long right = Long.MAX_VALUE;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (count(mid, times) < N) 
                left = mid+1;
            else {
                answer = mid;
                right = mid-1;
            }            
        }
    }
    
    static long count(long mid, int[] times) {
        long result = 0;
        
        for (int i = 0 ; i < times.length ; i++) {
            result += mid / times[i];
            
            if (result > N) return N+1;
        }
        
        return result;
    }
}