import java.util.*;

class Solution {
    
    static int answer;
    static int[] copiedRocks;
    
    public int solution(int distance, int[] rocks, int n) {
        answer = 0;
        copiedRocks = new int[rocks.length + 1];
        Arrays.sort(rocks);
        for (int i = 0 ; i < rocks.length ; i++)
            copiedRocks[i] = rocks[i];
        
        copiedRocks[rocks.length] = distance;
        
        solve(distance, n);
        
        return answer;
    }
    
    static void solve(int distance, int n) {
        int left = 1;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int cnt = 0;
            int cur = 0;
            
            for (int i = 0 ; i < copiedRocks.length ; i++) {
                int next = copiedRocks[i];
                
                if (next - cur < mid) cnt++;
                else cur = next;
                
                if (cnt > n) break;
            }
            
            
            
            if (cnt <= n) {
                answer = mid;
                left = mid+1;
            }
            else right = mid-1;
        }
    }
}