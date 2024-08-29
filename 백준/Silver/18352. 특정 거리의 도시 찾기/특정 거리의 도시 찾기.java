import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K, X;
    static int[] dist;
    static StringBuilder sb;
    static List<List<Node>> adj;
    static class Node {
        int idx, cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        adj = new ArrayList<>();
        dist = new int[N+1];

        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, 0));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        solve();

        System.out.println(sb.length() == 0 ? -1 : sb);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });
        boolean[] visited = new boolean[N+1];
        PQ.add(new Node(X, 0));
        dist[X] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            for (Node next : adj.get(now.idx)) {
                if (!visited[next.idx] && dist[next.idx] > dist[now.idx] + 1) {
                    dist[next.idx] = dist[now.idx] + 1;
                    PQ.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (dist[i] == K) sb.append(i).append("\n");
        }

    }
}