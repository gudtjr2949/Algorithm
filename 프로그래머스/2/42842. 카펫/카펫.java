class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int S = brown + yellow;
        
        for (int width = 3 ; width < S ; width++) {
            if (S % width != 0) continue;
            int height = S / width;
            
            // 노란색 카펫 면적
            int yellowS = (width - 2) * (height - 2);
            
            // 갈색 카펫 면적
            int brownS = S - yellowS;
            
            if (brownS == brown && yellowS == yellow) {
                answer[0] = Math.max(width, height);
                answer[1] = Math.min(width, height);
                break;
            }
        }
        
        return answer;
    }
}