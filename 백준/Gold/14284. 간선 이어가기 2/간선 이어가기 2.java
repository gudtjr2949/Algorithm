import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S, T;
    static int[] dist;
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

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        dist = new int[N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(bf.readLine());

        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, Integer.MAX_VALUE);

        solve();

        System.out.println(dist[T]);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });
        boolean[] visited = new boolean[N+1];
        PQ.add(new Node(S, 0));
        dist[S] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == T) return;

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            for (Node next : adj.get(now.idx)) {
                if (!visited[next.idx] && dist[next.idx] > dist[now.idx] + next.cnt) {
                    dist[next.idx] = dist[now.idx] + next.cnt;
                    PQ.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}