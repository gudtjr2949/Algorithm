import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, E, V1, V2;
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
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }

        st = new StringTokenizer(bf.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());


        int min1 = bfs(1, V1) + bfs(V1, V2) + bfs(V2, N);
        int min2 = bfs(1, V2) + bfs(V2, V1) + bfs(V1, N);

        if (min1 < 0 && min2 < 0) System.out.println(-1);
        else System.out.println(Math.min(min1, min2));
    }

    static int bfs(int start, int end) {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int[] dist = new int[N+1];
        Arrays.fill(dist, 100_000_000);
        PQ.add(new Node(start, 0));
        dist[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == end) return now.cost;

            for (Node next : adj.get(now.idx)) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    PQ.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return -100_000_000;
    }
}