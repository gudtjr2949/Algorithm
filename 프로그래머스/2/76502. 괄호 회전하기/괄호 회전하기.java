import java.util.*;

class Solution {
        
    public int solution(String s) {
        int answer = 0;
        
        Queue<Character> Q = new LinkedList<>();
 
        for (int i = 0 ; i < s.length() ; i++) {
            Q.add(s.charAt(i));
        }
        
        // Queue 를 사용해 문자열 회전 -> 회전을 끝내고, stack 을 사용해 올바른지 아닌지 판별
        for (int i = 0 ; i < s.length() ; i++) {
            if (checkQ(Q)) answer++;
            char tmp = Q.poll();
            Q.add(tmp);
        }
        
        return answer;
    }
    
    static boolean checkQ(Queue<Character> Q) {
        Queue<Character> tmpQ = new LinkedList<>();
        
        int size = Q.size();
        
        for (int i = 0; i < size; i++) {
            char ch = Q.poll();
            tmpQ.offer(ch);
            Q.offer(ch); 
        }
        
        if (tmpQ.peek() == '}' || tmpQ.peek() == ']' || tmpQ.peek() == ')') return false;
        
        Stack<Character> stack = new Stack<>();
        
        size = tmpQ.size();
        
        for (int i = 0 ; i < size ; i++) {
            char c = tmpQ.poll();
            if (c == '[' || c == '{' || c == '(') { // 여는 괄호
                stack.add(c);
            } else {
                if (stack.size() == 0) return false;
                
                if ((c == ']' && stack.peek() == '[') 
                    || (c == '}' && stack.peek() == '{') 
                    || (c == ')' && stack.peek() == '(')) {
                    stack.pop();
                } else {
                    break;
                }
            }
        }
        
        if (stack.size() != 0) return false;
    
        return true;
    }
}