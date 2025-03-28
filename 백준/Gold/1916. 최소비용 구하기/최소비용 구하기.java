import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, start, end;
    static int[] dp;
    static List<List<Node>> adj;
    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[end]);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        PQ.add(new Node(start, 0));
        boolean[] visited = new boolean[N+1];

        dp[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (!visited[now.idx]) {
                visited[now.idx] = true;

                for (Node next : adj.get(now.idx)) {
                    if (!visited[next.idx] && dp[next.idx] > dp[now.idx] + next.cost) {
                        dp[next.idx] = dp[now.idx] + next.cost;
                        PQ.add(new Node(next.idx, dp[next.idx]));
                    }
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void init() {
        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }
    }
}