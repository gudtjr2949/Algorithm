import java.util.*;

class Solution {
    
    static int N;
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
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        N = n;
        
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) adj.add(new ArrayList<>());
        
        for (int i = 0 ; i < fares.length ; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }
        
        int[] S = solve(s);
        int[] A = solve(a);
        int[] B = solve(b);
        
        answer = Integer.MAX_VALUE;
                
        for (int i = 1 ; i <= n ; i++) {
            answer = Math.min(answer, S[i] + A[i] + B[i]);
        }
                
        return answer;
    }
    
    static int[] solve(int start) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.add(new Node(start, 0));
        
        boolean[] visited = new boolean[N+1];
        int[] dp = new int[N+1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;
        
        while(!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (!visited[now.v]) visited[now.v] = true;
            
            for (Node next : adj.get(now.v)) {
                if (!visited[next.v] && dp[next.v] > now.cost + next.cost) {
                    dp[next.v] = now.cost + next.cost;
                    PQ.add(new Node(next.v, dp[next.v]));
                }
            }
        }
        
        return dp;
    }
}