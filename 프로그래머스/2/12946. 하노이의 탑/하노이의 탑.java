import java.util.*;

class Solution {
    
    static List<Node> list;
    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int[][] solution(int n) {
        list = new ArrayList<>();
        solve(n, 1, 2, 3);
        
        int[][] answer = new int[list.size()][2];
        
        int idx = 0;
        
        for (Node now : list) {
            answer[idx][0] = now.start;
            answer[idx][1] = now.end;
            idx++;
        }
        
        return answer;
    }
    
    static void solve(int n, int start, int mid, int end) {
        if (n == 1) {
            list.add(new Node(start, end));
            return;
        }
        
        solve(n-1, start, end, mid);
        list.add(new Node(start, end));
        solve(n-1, mid, start, end);
    }
}