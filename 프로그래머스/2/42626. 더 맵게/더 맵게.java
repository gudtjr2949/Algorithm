import java.util.*;

class Solution {
    
    static int N, answer;
    static Queue<Integer> PQ;
    
    public int solution(int[] scoville, int K) {
        init();
        solve(scoville, K);
        return answer;
    }
    
    static void solve(int[] scoville, int K) {
        for (int spicy : scoville) {
            PQ.add(spicy);
        }
        
        while (PQ.size() >= 2 && PQ.peek() < K) {
            int a = PQ.poll();
            int b = PQ.poll();
            int mixed = a + (b * 2);
            PQ.add(mixed);
            answer++;
        }
        
        if (PQ.peek() < K) answer = -1;
    }
    
    static void init() {
        PQ = new PriorityQueue<>();
    }
}