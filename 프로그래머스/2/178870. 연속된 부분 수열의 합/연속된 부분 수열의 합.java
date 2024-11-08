import java.util.*;

class Solution {
    
    static int N;
    static int[] answer;
    
    static class Node {
        int left, right, length;
        public Node(int left, int right, int length) {
            this.left = left;
            this.right = right;
            this.length = length;
        }
    }
  
    public int[] solution(int[] sequence, int k) {
        N = sequence.length;
        answer = new int[2];
        
        solve(sequence, k);
        
        return answer;
    }
    
    static void solve(int[] sequence, int k) {
        int left = 0;
        int right = 0;
        
        List<Node> list = new ArrayList<>();
        
        int sum = 0;
        
        while (left < N) {
            if (sum > k || right == N) {
                sum -= sequence[left++];
            } else {
                sum += sequence[right++];
            }
                        
            if (sum == k) {
                list.add(new Node(left, right-1, right-left));
            }
        }
        
        Collections.sort(list, (o1, o2) -> {
            if (o1.length == o2.length) return o1.left - o2.left;
            else return o1.length - o2.length;
        });
        
        answer[0] = list.get(0).left;
        answer[1] = list.get(0).right;
    }
}