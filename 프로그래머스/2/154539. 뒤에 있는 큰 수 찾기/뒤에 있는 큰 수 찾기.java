import java.util.*;

class Solution {
    static class Node {
        int idx, num;
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        
        Stack<Node> stack = new Stack<>();
        
        for (int i = 0 ; i < size ; i++) {
            while (!stack.isEmpty() && stack.peek().num < numbers[i]) {
                answer[stack.pop().idx] = numbers[i];
            }
            stack.add(new Node(i, numbers[i]));
        }
        
        for (int i = 0 ; i < size ; i++) {
            if (answer[i] == 0) answer[i] = -1;
        }
        
        return answer;
    }
}