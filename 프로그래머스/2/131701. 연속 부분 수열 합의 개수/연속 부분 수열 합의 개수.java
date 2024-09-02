import java.util.*;

class Solution {
    
    static int start;
    static boolean first;
    static int[] element;
    static Set<Integer> set;
    
    public int solution(int[] elements) {
        int answer = 0;

        set = new HashSet<>();
        
        element = elements;
        
        for (int i = 0 ; i < element.length ; i++) {
            start = i;
            first = true;
            dfs(i, element[i]);
        }
                
        return set.size();
    }
    
    static void dfs(int idx, int sum) {
        if (!first && idx == start) return;
        set.add(sum);
        
        if (idx == element.length-1) idx = -1;
                
        first = false;
        
        dfs(idx+1, sum + element[idx+1]);
    }
}