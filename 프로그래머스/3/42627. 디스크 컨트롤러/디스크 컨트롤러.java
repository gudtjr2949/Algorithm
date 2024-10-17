import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        
        PriorityQueue<int[]> PQ = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        int idx = 0;
        int end = 0;
        int cnt = 0;
        
        while (cnt < jobs.length) {
            
            while (idx < jobs.length && end >= jobs[idx][0]) {
                PQ.add(jobs[idx++]);
            }
            
            if (PQ.isEmpty()) {
                end = jobs[idx][0];
            } else {
                int[] tmp = PQ.poll();
                answer += (end - tmp[0]) + tmp[1];
                end += tmp[1];
                cnt++;
            }
        }
        
        answer = (int) Math.floor(answer / jobs.length);
        
        return answer;
    }
}