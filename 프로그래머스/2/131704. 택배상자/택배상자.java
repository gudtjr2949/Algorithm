import java.util.*;

class Solution {
    
    static int answer;
    
    public int solution(int[] order) {
        answer = 0;
        
        solve(order);
        
        return answer;
    }
    
    static void solve(int[] order) {
        Stack<Integer> stack = new Stack<>();
        
        int idx = 0; // orde 용 idx
        int nowBox = 0;
        
        while (nowBox <= order.length && idx < order.length) {
            nowBox++;
            
            if (order[idx] == nowBox) {
                answer++;
                idx++;
            } else {
                stack.add(nowBox);
            }
            
            while (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                answer++;
                idx++;
            }
        }
        
    }
}