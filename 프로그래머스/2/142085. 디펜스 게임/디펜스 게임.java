import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        if (k >= enemy.length) {
            answer = enemy.length;
        } else {
            answer = solve(n, k, enemy);
        }
        
        return answer;
    }
    
    static int solve(int n, int k, int[] enemy) {
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0 ; i < enemy.length ; i++) {
            n -= enemy[i];
            PQ.add(enemy[i]);
            
            if (n < 0) {
                if (k > 0) {
                    n += PQ.poll();
                    k--;
                } else {
                    return i;
                }
            }
        }
        
        return enemy.length;
    }
}