import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        int left, right;
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
        
        public int compareTo(Node n) {
            int size1 = this.right - this.left;
            int size2 = n.right - n.left;
            
            if (size1 == size2) {
                return this.left - n.left;
            } else {
                return size1 - size2;
            }
        }
    }
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        List<Node> list = new ArrayList<>();
        
        int left = 0;
        int right = 0;
        int sum = 0;
        
        while (left < sequence.length) {
            if(sum > k || right == sequence.length) {
                sum -= sequence[left++];
            } else {
                sum += sequence[right++];
            }
                
            if (sum == k) {
                list.add(new Node(left, right-1));
            }
        }
        
        Collections.sort(list);
        
        answer[0] = list.get(0).left;
        answer[1] = list.get(0).right;
        
        return answer;
    }
}