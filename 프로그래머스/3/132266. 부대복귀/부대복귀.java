import java.util.*;

class Solution {
    
    static int MAX = 100_001;
    static int[] dp;
    static List<List<Integer>> adj;
    
    static class Node {
        int idx, cnt;
        
        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination){
        int[] answer = new int[sources.length];
        dp = new int[n+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) adj.add(new ArrayList<>());
        
        for (int i = 0 ; i < roads.length ; i++) {
            int x = roads[i][0];
            int y = roads[i][1];
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        
        solve(n, destination);
        
        for (int i = 0 ; i < sources.length ; i++) {
            if (dp[sources[i]] == MAX) answer[i] = -1;
            else answer[i] = dp[sources[i]];
        }
        
        return answer;
    }
    
    static void solve(int n, int destination) {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<>(){
           @Override
            public int compare(Node n1, Node n2) {
                return n1.cnt - n2.cnt;
            }
        });
        Arrays.fill(dp, MAX);
        PQ.add(new Node(destination, 0));
        dp[destination] = 0;
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            for (Integer next : adj.get(now.idx)) {
                if (dp[next] > dp[now.idx] + 1) {
                    dp[next] = dp[now.idx] + 1;
                    PQ.add(new Node(next, dp[next]));
                }
            }
        }
    }
}