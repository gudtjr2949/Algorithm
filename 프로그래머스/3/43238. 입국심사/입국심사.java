import java.util.*;

class Solution {
    
    static int N;
    static long answer;
    static int[] copiedTimes;
    
    public long solution(int n, int[] times) {
        N = n;
        answer = 0;
        
        copiedTimes = times;
        Arrays.sort(copiedTimes);
        
        solve();
        
        return answer;
    }
    
    static void solve() {
        long left = 0;
        long right = Long.MAX_VALUE;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (count(mid) >= N) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        answer = left;
    }
    
    static long count(long mid) {
        long result = 0;
        for (int i = 0 ; i < copiedTimes.length ; i++) {
            result += mid / copiedTimes[i];
            
            if (result > N) return N+1;
        }
        
        return result;
    }
    
}