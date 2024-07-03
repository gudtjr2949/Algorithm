import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        PriorityQueue<Integer> APQ = new PriorityQueue<>(Collections.reverseOrder());   
        PriorityQueue<Integer> BPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0 ; i < A.length ; i++) {
            APQ.add(A[i]);
            BPQ.add(B[i]);
        }
        
        for (int i = 0 ; i < A.length ; i++) {
            if (APQ.peek() < BPQ.peek()) {
                answer++;
                APQ.poll();
                BPQ.poll();
            } else {
                APQ.poll();
            }
        }
        
        return answer;
    }
}