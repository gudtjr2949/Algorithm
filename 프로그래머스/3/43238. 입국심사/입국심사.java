import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        answer = binarySearch(n, times);
        
        return answer;
    }
    
    static long binarySearch(int n, int[] times) {
        long left = 0;
        long right = (long) n * times[times.length-1];
        long answer = 0;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            long people = 0;
            
            for (int i = 0 ; i < times.length ; i++) 
                people += mid / times[i];
            
            if (people < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    // 만약 mid 초 만큼의 시간이 주어졌을 때, 각 심사관이 심사할 수 있는 사람 수
    static long solve(int mid, int[] times, int n) {
        int cnt = 0;
        
        for (int i = 0 ; i < times.length ; i++) {
            cnt += mid / (long) times[i];
            
            if (cnt >= n) {
                return cnt;
            }
        }
        
        return cnt;
    }
}