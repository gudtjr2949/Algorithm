import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int wh1 = scores[0][0];
        int wh2 = scores[0][1];
        
        int N = scores.length;
        
        Arrays.sort(scores, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            }
        });
        
        int maxScore = scores[0][1];
        
        for(int i = 1 ; i < N ; i++) {
           if (scores[i][1] < maxScore) {
               if (scores[i][0] == wh1 && scores[i][1] == wh2) 
                   return -1;
               
               scores[i][0] = -1;
               scores[i][1] = -1;
           } else {
                maxScore = scores[i][1];
           }
        }
        
        Arrays.sort(scores, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[0] + o2[1]) - (o1[0] + o1[1]);
            }
        });
                
        for(int i = 0 ; i < N ; i++) {
            if (scores[i][0] + scores[i][1] > wh1 + wh2) {
                answer++;
            } else {
                break;
            }
        }
                
        return answer;
    }
    
}