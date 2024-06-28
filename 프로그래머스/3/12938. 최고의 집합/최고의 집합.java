import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if (n > s) {
          answer = new int[1];
          answer[0] = -1;
        } else {
            int first = s / n;
            int rest = s % n;

            Arrays.fill(answer, first);

            if (rest != 0) {
                for (int i = 0 ; i < n ; i++) {
                    answer[i]++;
                    rest--;
                    if (rest == 0) break;
                }
            }

            Arrays.sort(answer);
        }
        
        return answer;
    }
}