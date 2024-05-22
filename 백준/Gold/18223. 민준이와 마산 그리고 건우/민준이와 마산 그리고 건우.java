import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, P;
    static int[] dist;
    static List<List<Node>> adj;
    static class Node implements Comparable<Node> {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
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

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++)
            adj.add(new ArrayList<>());

        dist = new int[V+1];

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        dijk(1);
        int tmp1 = dist[V]; // 건우 -> 마산까지 최단거리
        int tmp2 = dist[P]; // 민준 -> 건우까지 최단거리

        dijk(P);
        int tmp3 = dist[V]; // 건우 -> 마산까지 최단거리

        if (tmp1 == (tmp2 + tmp3)) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    static void dijk(int start) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        boolean[] visited = new boolean[V+1];

        PQ.add(new Node(start, 0));
        Arrays.fill(dist, 100_000_000);

        dist[start] = 0;
        visited[start] = true;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.dest == V) {
                return;
            }

            if (!visited[now.dest]) visited[now.dest] = true;

            for (Node next : adj.get(now.dest)) {
                if (!visited[next.dest] && dist[next.dest] > dist[now.dest] + next.cost) {
                    dist[next.dest] = dist[now.dest] + next.cost;
                    PQ.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }
    }
}