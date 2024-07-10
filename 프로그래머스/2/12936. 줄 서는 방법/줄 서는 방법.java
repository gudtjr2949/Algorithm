import java.util.*;

class Solution {
    
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        
        List<Integer> list = new ArrayList<>();
        
        // 배열 수
        long cycle = 1;
        for (int i = 1 ; i <= n ; i++) {
            list.add(i);
            cycle *= i;
        }
        
        int idx = 0;
        // list 는 0이 아니라 1부터 시작하기 때문에 k를 -1 해야함
        k--;
        while (idx < n) {
            cycle /= n - idx; // 주기
            answer[idx++] = list.remove((int) (k / cycle));
            k %= cycle;
        }
       
                
        return answer;
    }
}