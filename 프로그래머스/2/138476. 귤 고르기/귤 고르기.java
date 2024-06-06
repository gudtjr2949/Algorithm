import java.util.*;

class Solution {
    
    static class Tan implements Comparable<Tan> {
        int size, cnt;
        public Tan(int size, int cnt) {
            this.size = size;
            this.cnt = cnt;
        }
        
        public int compareTo(Tan t) {
            return t.cnt - this.cnt;
        }
    }
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        List<Tan> list = new ArrayList<>();
        
        int max = 0;
        for (int i = 0 ; i < tangerine.length ; i++) {
            max = Math.max(tangerine[i], max);
        }
        
        int[] cntArr = new int[max+1];
        
        for (int i = 0 ; i < tangerine.length ; i++) {
            cntArr[tangerine[i]]++;
        }
        
        for (int i = 1 ; i < cntArr.length ; i++) {
            list.add(new Tan(i, cntArr[i]));
        }
        
        Collections.sort(list);
        
        int tmp = 0;
        for (Tan tan : list) {
            answer++;
            tmp += tan.cnt;
            if (tmp >= k) break;
        }
        
        return answer;
    }
}