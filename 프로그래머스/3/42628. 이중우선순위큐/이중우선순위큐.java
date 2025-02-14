import java.util.*;

class Solution {
    
    static int[] answer;
    static Queue<Integer> min, max;
    
    public int[] solution(String[] operations) {
        init();
        solve(operations);
        return answer;
    }
    
    static void solve(String[] operations) {
        for (String s : operations) {
            String[] tmp = s.split(" ");
            char operation = tmp[0].charAt(0);
            int num = Integer.parseInt(tmp[1]);
            
            if (operation == 'I') {
                max.add(num);
                min.add(num);
            } else {
                if (max.isEmpty() || min.isEmpty()) continue;
                
                if (num == -1) {
                    int removed = min.poll();
                    max.remove(removed);
                } else {
                    int removed = max.poll();
                    min.remove(removed);
                }
            }
        }
        
        if (!max.isEmpty() && !min.isEmpty()) {
            answer[0] = max.peek();
            answer[1] = min.peek();
        }
    }
    
    static void init() {
        answer = new int[2];
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Collections.reverseOrder());
    }
}