import java.util.*;

class Solution {
    
    static int answer;
    static Map<String, Integer> map;
    
    public int solution(String[][] clothes) {
        answer = 1;
        
        map = new HashMap<>();
        
        for (int i = 0 ; i < clothes.length ; i++) {
            if (!map.containsKey(clothes[i][clothes[i].length-1])) {
                map.put(clothes[i][clothes[i].length-1], 1);
            } else {
                int cnt = map.get(clothes[i][clothes[i].length-1]);
                map.put(clothes[i][clothes[i].length-1], cnt+1);
            }
        }
        
        Iterator<Integer> iter = map.values().iterator(); 
        while(iter.hasNext()) {
            answer *= iter.next().intValue() + 1;
        }
        
        return answer-1;
    }
    
   
    
}