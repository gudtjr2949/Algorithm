import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 2;
        int[] lMin = new int[a.length];
        int[] rMin = new int[a.length];
        
        int min = a[0];
        for (int i = 1 ; i < a.length ; i++) {
            min = Math.min(min, a[i]);
            lMin[i] = min;
        }
        
        min = a[a.length-1];
        for (int i = a.length-2 ; i >= 0 ; i--) {
            min = Math.min(min, a[i]);
            rMin[i] = min;
        }
        
        if (a.length == 1) return 1;
        
        for (int i = 1 ; i < a.length-1 ; i++) {
            if (a[i] > rMin[i] && a[i] > lMin[i]) continue;
            answer++;
        }
        
        
        return answer;
    }
}