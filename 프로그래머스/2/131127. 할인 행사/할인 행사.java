import java.util.*;

class Solution {
    
    static int answer;
    static Map<String, Integer> idxMap = new HashMap<>();
    static List<Things> list;
    static int[] cnt;
    
    static class Things {
        String want;
        int number;
        public Things(String want, int number) {
            this.want = want;
            this.number = number;
        }
    }
    
    public int solution(String[] want, int[] number, String[] discount) {        
        list = new ArrayList<>();
        
        for (int i = 0 ; i < want.length ; i++) {
            list.add(new Things(want[i], number[i]));
        }
        
        int idx = 0;
        
        for (int i = 0 ; i < want.length ; i++) {
            if (!idxMap.containsKey(want[i])) {
                idxMap.put(want[i], idx++);
            }
        }
        
         for (int i = 0 ; i < discount.length ; i++) {
            if (!idxMap.containsKey(discount[i])) {
                idxMap.put(discount[i], idx++);
            }
        }
        
        cnt = new int[idx];
        
        sliding(discount);
        
        return answer;
    }
    
    
    static void sliding(String[] discount) {
        // 처음 10일 cnt 에 넣기
        for (int i = 0 ; i < 10 ; i++) {
            cnt[idxMap.get(discount[i])]++;
        }
        
        // 체크
        if (check()) answer++;
        
        // 슬라이딩 윈도우
        int left = 0;
        int right = 10;
        
        while(right < discount.length) {
            // left 빼기
            cnt[idxMap.get(discount[left++])]--;
            
            // right 더하기
            cnt[idxMap.get(discount[right++])]++;
            
            // 체크
            if (check()) answer++;
        }
    }
    
    static boolean check() {
        for (Things thing : list) {            
            if (cnt[idxMap.get(thing.want)] < thing.number) return false;
        }
        
        return true;
    }
}