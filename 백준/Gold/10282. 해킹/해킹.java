import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, D, C, MAX = 10_000_001;
    static StringBuilder sb = new StringBuilder();
    static int[] dp;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node {
        int idx, time;
        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            input(bf);
            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        dp[C] = 0;
        PQ.add(new Node(C, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            for (Node next : adj.get(now.idx)) {
                if (!visited[next.idx] && dp[next.idx] > now.time + next.time) {
                    dp[next.idx] = now.time + next.time;
                    PQ.add(new Node(next.idx, dp[next.idx]));
                }
            }
        }

        int infection = 0;
        int time = 0;

        for (int i = 1 ; i <= N ; i++) {
            if (visited[i]) infection++;

            if (dp[i] != MAX) {
                time = Math.max(time, dp[i]);
            }
        }

        sb.append(infection).append(" ").append(time).append("\n");
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < D ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            adj.get(to).add(new Node(from, time));
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
        visited = new boolean[N+1];
        dp = new int[N+1];
        Arrays.fill(dp, MAX);
    }
}