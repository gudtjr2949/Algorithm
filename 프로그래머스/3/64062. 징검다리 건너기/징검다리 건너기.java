import java.util.*;

class Solution {
    
    static class Node {
        int idx, value;
        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
    
    public int solution(int[] stones, int k) {
        int answer = 200_000_001;
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n2.value - n1.value);
        
        for (int i = 0 ; i < k-1 ; i++) {    
            PQ.add(new Node(i, stones[i]));
        }
        
        for (int i = k-1 ; i < stones.length ; i++) {
            PQ.add(new Node(i, stones[i]));
            while (PQ.peek().idx <= i-k) 
                PQ.poll();
            
            answer = Math.min(PQ.peek().value, answer);
        }
        
        return answer;
    }
}