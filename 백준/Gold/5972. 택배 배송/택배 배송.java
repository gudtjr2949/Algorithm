import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
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

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }

        dijk();

        System.out.println(dp[N]);
    }

    static void dijk() {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        PQ.add(new Node(1, 0));
        visited[1] = true;
        dp[1] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (!visited[now.v]) {
                visited[now.v] = true;
            }

            for (Node next : adj.get(now.v)) {
                if (!visited[next.v] && dp[next.v] > now.cost + next.cost) {
                    dp[next.v] = now.cost + next.cost;
                    PQ.add(new Node(next.v, dp[next.v]));
                }
            }
        }
    }
}