import java.util.*;

class Solution {
    
    static int[] answer;
    static Queue<Node> Q = new LinkedList<>();
    static class Node {
        int progress, speed;
        public Node(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
    
    public int[] solution(int[] progresses, int[] speeds) {    
        for (int i = 0 ; i < progresses.length ; i++) {
            Q.add(new Node(progresses[i], speeds[i]));
        }
        
        solve();
        
        
        
        return answer;
    }
    
    static void solve() {
        int[] arr = new int[Q.size()];
        
        int idx = 0;
        int day = 0;
        int max = 0;
        
        while (!Q.isEmpty()) {
            Node now = Q.poll();
            
            if ((100 - now.progress) % now.speed != 0) {
                day = ((100 - now.progress) / now.speed) + 1;
            } else {
                day = (100 - now.progress) / now.speed;
            }
            
            // 처음
            if (max == 0) {
                max = day;
            }
            
            if (day <= max) {
                arr[idx]++;
            } else {
                idx++;
                arr[idx]++;
                max = day;
            }
        }
        
        answer = new int[idx+1];
        
        for (int i = 0 ; i <= idx ; i++) {
            answer[i] = arr[i];
        }
        
        
    }
}