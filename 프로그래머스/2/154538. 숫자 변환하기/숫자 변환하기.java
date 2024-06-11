import java.util.*;

class Solution {
    
    static int answer = -1;
    
    static class Node implements Comparable<Node> {
        int num, cnt;
        
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
    
    public int solution(int x, int y, int n) {
        
        bfs(x, y, n);
        
        return answer;
    }
    
    static void bfs(int x, int y, int n) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        
        PQ.add(new Node(y, 0));
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (now.num == x) {
                answer = now.cnt;
                return;
            }
            
            if (now.num - n >= x) 
                PQ.add(new Node(now.num - n, now.cnt + 1));
            
            if (now.num % 2 == 0)
                PQ.add(new Node(now.num / 2, now.cnt + 1));
            
            if (now.num % 3 == 0)
                PQ.add(new Node(now.num / 3, now.cnt + 1));
            
        }
    }
    

}