import java.util.*;

class Solution {
    
    static int answer;
    static int[] pick;
    static String[] mineral;
    static int[][] consumption = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    
    public int solution(int[] picks, String[] minerals) {
        answer = 12501;
        
        pick = picks;
        mineral = minerals;
        
        dfs(0, 0);
        
        return answer;
    }
    
    static void dfs(int idx, int tired) {
        if (check() || idx >= mineral.length) {
            answer = Math.min(answer, tired);
            return;
        }
            
        for (int i = 0 ; i < 3 ; i++) {
            if (pick[i] <= 0) continue;
            
            int sum = 0;
            for (int j = idx ; j < idx+5 ; j++) {
                if (j >= mineral.length) break;
                sum += consumption[i][findIdx(mineral[j])];
            }
            
            pick[i]--;
            dfs(idx+5, tired+sum);
            pick[i]++;
        }
    }
    
    static int findIdx(String s) {
        if (s.equals("diamond")) return 0;
        else if (s.equals("iron")) return 1;
        else return 2;
    }
    
    static boolean check() {        
        for (int i = 0 ; i < pick.length ; i++) {
            if (pick[i] > 0) {
                return false;
            }
        }
        
        return true;
    }
}