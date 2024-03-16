import java.util.*;

class Solution {
    
    static int N, MAX = 50_001;
    static int[] dp;
    static List<List<Node>> adj;
    static class Node implements Comparable<Node> {
        int v, cost;
        
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        N = n;
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }
        
        dp = new int[n+1];
        Arrays.fill(dp, MAX);
        
        for (int i = 0 ; i < edge.length ; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            adj.get(a).add(new Node(b, 1));
            adj.get(b).add(new Node(a, 1));
        }
        
        dijk();
        
        answer = findAnswer();
        
        return answer;
    }
    
    static void dijk() {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.add(new Node(1, 0));
        boolean[] visited = new boolean[N+1];
        dp[1] = 0;
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (!visited[now.v]) visited[now.v] = true;
            
            for (Node next : adj.get(now.v)) {
                if (!visited[next.v] && dp[next.v] > now.cost + next.cost) {
                    dp[next.v] = now.cost + next.cost;
                    PQ.add(new Node(next.v, dp[next.v]));
                }
            }
        }
    }
    
    static int findAnswer() {
        int max = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (dp[i] != MAX) {
                max = Math.max(max, dp[i]);
            }
        }
        
        int cnt = 0;
        
        for (int i = 1 ; i <= N ; i++) {
            if (dp[i] == max) cnt++;
        }
        
        return cnt;
    }
    
}