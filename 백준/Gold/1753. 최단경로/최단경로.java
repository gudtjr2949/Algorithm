import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, K;
    static int[] dist;
    static List<List<Node>> adj;
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        dist = new int[V+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++)
            adj.add(new ArrayList<>());

        K = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        solve();

        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i <= V ; i++) {
            if (dist[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        
        PQ.add(new Node(K, 0));
        dist[K] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (Node next : adj.get(now.idx)) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    PQ.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}