import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
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
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Node(to, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());

        System.out.println(dijk(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    private static int dijk(int start, int end) {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];

        Arrays.fill(dist, 100_000_001);
        dist[start] = 0;

        PQ.add(new Node(start, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            if (now.idx == end) break;

            for (Node next : adj.get(now.idx)) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    PQ.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        
        return dist[end];
    }
}