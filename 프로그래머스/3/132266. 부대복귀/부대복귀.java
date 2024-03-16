import java.util.*;

class Solution {
    
    static int N, end;
    static int[] dp;
    static List<List<Node>> adj;
    static class Node implements Comparable<Node>{
        
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
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination){
        int[] answer = new int[sources.length];
        
        N = n;
        end = destination;
        
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < roads.length ; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            adj.get(from).add(new Node(to, 1));
            adj.get(to).add(new Node(from, 1));
        }
        
        dijk(destination);
        
        for (int i = 0 ; i < sources.length ; i++) {
            answer[i] = dp[sources[i]] == Integer.MAX_VALUE ? -1 : dp[sources[i]];
        }
        
        return answer;
    }
    
    static void dijk(int end) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.add(new Node(end, 0));
        
        dp = new int[N+1];
        boolean[] visited = new boolean[N+1];
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[end] = 0;
        
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
    }
}