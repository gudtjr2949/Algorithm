import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, D;
    static int[] dp;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node implements Comparable<Node> {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
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
        D = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0 ; i <= D ; i++) adj.add(new ArrayList<>());
        dp = new int[D+1];
        Arrays.fill(dp, 100_001);

        visited = new boolean[D+1];

        for (int i = 0 ; i <= D ; i++) {
            adj.get(i).add(new Node(i+1, 1));
        }

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (end <= D) {
                adj.get(start).add(new Node(end, cost));
            }
        }

        solve();

        System.out.println(dp[D]);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        PQ.add(new Node(0, 0));
        visited[0] = true;
        dp[0] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (!visited[now.v]) visited[now.v] = true;

            for (Node next : adj.get(now.v)) {
                if (next.v <= D && !visited[next.v] && dp[next.v] > now.cost + next.cost) {
                    dp[next.v] = now.cost + next.cost;
                    PQ.add(new Node(next.v, dp[next.v]));
                }
            }
        }
    }
}