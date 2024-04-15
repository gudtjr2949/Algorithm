class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int bigMax = 0; // 큰 수중 가장 큰 수
        int smallMax = 0; // 작은 수 중 가장 큰 수
        
        for (int i = 0 ; i < sizes.length ; i++) {
            if (sizes[i][0] > sizes[i][1]) {
                bigMax = Math.max(bigMax, sizes[i][0]);
                smallMax = Math.max(smallMax, sizes[i][1]);
            } else {
                bigMax = Math.max(bigMax, sizes[i][1]);
                smallMax = Math.max(smallMax, sizes[i][0]);
            }
        }
        
        answer = bigMax * smallMax;
        
        return answer;
    }
}