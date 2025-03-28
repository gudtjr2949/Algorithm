import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E, K, MAX = 100_000_000;
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
        printAnswer();
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        PQ.add(new Node(K, 0));
        dp[K] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (Node next : adj.get(now.idx)) {
                if (dp[next.idx] > dp[now.idx] + next.cost) {
                    dp[next.idx] = dp[now.idx] + next.cost;
                    PQ.add(new Node(next.idx, dp[next.idx]));
                }
            }
        }
    }

    static void printAnswer() {
        for (int i = 1 ; i <= V ; i++) {
            if (dp[i] == MAX) System.out.println("INF");
            else System.out.println(dp[i]);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        init();
        K = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++) {
            adj.add(new ArrayList<>());
        }
        dp = new int[V+1];
        Arrays.fill(dp, MAX);
    }
}