import java.util.*;

class Solution {
    
    static int answer;
    static int[] arr;
    
    public int solution(int distance, int[] rocks, int n) {
        arr = new int[rocks.length+1];
        for (int i = 0 ; i < rocks.length ; i++) {
            arr[i] = rocks[i];
        }
        arr[rocks.length] = distance;
        
        Arrays.sort(arr);
        
        solve(distance, n);
        
        return answer;
    }
    
    static void solve(int distance, int n) {
        int left = 0;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (count(mid) <= n) {
                answer = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
    }
    
    static int count(int mid) {
        int cur = 0;
        int cnt = 0;
        
        for (int i = 0 ; i < arr.length ; i++) {
            int next = arr[i];
            
            if (next - cur < mid) cnt++;
            else cur = next;
            
        }
        
        return cnt;
    }
}