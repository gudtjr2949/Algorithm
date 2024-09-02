import java.util.*;

class Solution {
    
    static int min;
    static int[] answer;
    static Set<String> set;
    static Map<String, Integer> map;
    
    public int[] solution(String[] gems) {
        answer = new int[2];
        
        set = new HashSet<>();
        map = new HashMap<>();
        
        int idx = 0;
        
        for (String gem : gems) {
            if (!set.contains(gem)) {
                set.add(gem);
                map.put(gem, idx++);
            }
        }
        
        solve(gems);
        
        return answer;
    }
    
    static void solve(String[] gems) {
        int left = 0;
        int right = 0;
        
        answer[0] = 0;
        answer[1] = gems.length;
        
        Set<String> tmpSet = new HashSet<>();
        int[] arr = new int[set.size()];
        
        tmpSet.add(gems[left]);
        arr[map.get(gems[left])]++;
        
        while (left < gems.length && right < gems.length) {            
            if (tmpSet.size() == set.size()) {
                if (answer[1] - answer[0] > right - left) {
                    answer[0] = left+1;
                    answer[1] = right+1;
                }
                
                arr[map.get(gems[left])]--;
                if (arr[map.get(gems[left])] == 0) tmpSet.remove(gems[left]);
                left++;
            } else {
                right++;
                if (right == gems.length) break;
                tmpSet.add(gems[right]);
                arr[map.get(gems[right])]++;
            }
        }
    }
}    