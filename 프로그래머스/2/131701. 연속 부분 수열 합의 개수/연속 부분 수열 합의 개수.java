import java.util.*;

class Solution {
    
    static int start;
    static boolean first;
    static Set<Integer> set;
    
    public int solution(int[] elements) {
        set = new HashSet<>();
        
        for (int i = 0 ; i < elements.length ; i++) {
            start = i;
            first = true;
            dfs(i, elements[i], elements);
        }
                
        return set.size();
    }
    
    static void dfs(int idx, int sum, int[] elements) {
        if (idx == start && !first) {
            return;
        }
        
        if (!set.contains(sum))
            set.add(sum);
        
        if (idx == elements.length-1) idx = -1;
        
        first = false;
        
        dfs(idx+1, sum + elements[idx+1], elements);
    }
    
}