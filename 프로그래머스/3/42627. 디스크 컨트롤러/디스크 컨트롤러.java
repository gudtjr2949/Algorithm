import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 작업들을 요청되는 시점을 기준으로 오름차순 정렬
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        // 작업들을 작업 소요 시간을 기준으로 오름차순 정렬
        PriorityQueue<int[]> PQ = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);
        
        int curIdx = 0;
        int cnt = 0;
        int end = 0;
        
        while (cnt < jobs.length) {
            
            // 하나의 작업이 완료되기 전(end)에 다른 작업이 요청을 한다면 PQ에 담음
            while (curIdx < jobs.length && jobs[curIdx][0] <= end) {
                PQ.add(jobs[curIdx++]);
            }
            
            // 작업이 수행되는 도중, 다른 요청이 들어오지 않는다면,
            if (PQ.isEmpty()) {
                end = jobs[curIdx][0];
            } else {
                int[] tmp = PQ.poll();
                answer += end - tmp[0] + tmp[1];
                end += tmp[1];
                cnt++;
            }
        }
        
        answer = (int) Math.floor(answer / jobs.length);
        
        return answer;
    }
}