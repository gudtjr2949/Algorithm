import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> PQ = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        
        
        for (int i = 0 ; i < works.length ; i++) PQ.add(works[i]);
        
        while (n > 0 && !PQ.isEmpty()) {
            int num = PQ.poll();
            
            if (num - 1 > 0) {
                PQ.add(num-1);
            }
            n--;
        }
        
        while (!PQ.isEmpty()) {
            int num = PQ.poll();
            answer += num*num;
        }
        
        return answer;
    }
}