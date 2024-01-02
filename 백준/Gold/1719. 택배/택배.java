import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX = 10000;
    static int[][] result;
    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static List<List<Node>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();

        for (int i = 0 ; i < N+1 ; i++) {
            adj.add(new ArrayList<>());
        }

        result = new int[N+1][N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }


        for (int i = 1 ; i < N+1 ; i++) {
            dijk(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i < N+1 ; i++) {
            for (int j = 1 ; j < N+1 ; j++) {
                if (result[i][j] == 0) {
                    sb.append("-").append(" ");
                } else {
                    sb.append(result[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dijk(int start) {
        boolean[] visited = new boolean[N+1];

        int[] dist = new int[N+1];

        for (int i = 0 ; i < N+1 ; i++) {
            dist[i] = MAX;
        }

        PriorityQueue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        PQ.add(new Node(start, 0));

        dist[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (!visited[now.v]) {
                visited[now.v] = true;
            }

            for (Node next : adj.get(now.v)) {
                // 방문하지 않았고, 최단경로인 경우
                if (!visited[next.v] && dist[next.v] > now.cost + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    result[next.v][start] = now.v;
                    PQ.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}