import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Queue<Integer> Q = new LinkedList<>();
        
        for (int i = 0 ; i < prices.length ; i++) 
            Q.add(prices[i]);
        
        int idx = 1;
        
        while (!Q.isEmpty()) {
            int cnt = 0;
            int num = Q.poll();
            
            for (int i = idx ; i < prices.length ; i++) {
                cnt++;
                if (prices[i] < num) break;
            }
            
            answer[idx-1] = cnt;
            idx++;
        }
        
        return answer;
    }
}