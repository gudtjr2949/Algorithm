class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int spreadSize = 2 * w + 1;
        
        int start = 1;
        
        for (int i = 0 ; i < stations.length ; i++) {
            int end = stations[i] - w - 1;
            
            if (start <= end) {
                int len = end - start + 1;
                
                if (len <= spreadSize) {
                    answer++;
                } else {
                    answer += len / spreadSize;
                    
                    if (len % spreadSize != 0) {
                        answer++;
                    }
                }
            }
            
            start = stations[i] + w + 1;
        }
        
        // 마지막으로 설치한 기지국의 전파가 마지막 아파트까지 도달하는지 체크
        if (start <= n) {
            int len = n - start;
            if (len <= spreadSize) {
                answer++;
            } else {
                answer += len / spreadSize;
                    
                if (len % spreadSize != 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}