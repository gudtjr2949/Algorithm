import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 체육복을 잃어버렸지만, 여분의 체육복을 가져온 경우
        for (int i = 0 ; i < lost.length ; i++) {
            for (int j = 0 ; j < reserve.length ; j++) {
                if (reserve[j] == lost[i]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        
        // 체육복을 잃어버렸고, 여분의 체육복도 없는 경우 -> 앞 뒤 학생에게 빌려야 함
        for (int i = 0 ; i < lost.length ; i++) {
            for (int j = 0 ; j < reserve.length ; j++) {
                if (reserve[j] == lost[i]-1 || reserve[j] == lost[i]+1) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        return answer;
    }
}