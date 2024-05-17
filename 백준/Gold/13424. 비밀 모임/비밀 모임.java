import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] dist;
    static List<List<Node>> adj;
    static class Node implements Comparable<Node>{
        int dest, len;

        public Node(int dest, int len) {
            this.dest = dest;
            this.len = len;
        }

        @Override
        public int compareTo(Node n) {
            return this.len - n.len;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>();

            for (int i = 0 ; i <= N ; i++)
                adj.add(new ArrayList<>());

            for (int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj.get(a).add(new Node(b, c));
                adj.get(b).add(new Node(a, c));
            }


            K = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());

            dist = new int[K][N+1];

            for (int i = 0 ; i < K ; i++) Arrays.fill(dist[i], 100_000_000);

            for (int i = 0 ; i < K ; i++) {
                dijk(Integer.parseInt(st.nextToken()), i);
            }

            int min = 100_000_000;
            int minIdx = 0;

            for (int i = 1 ; i <= N ; i++) {
                int sum = 0;
                for (int j = 0 ; j < K ; j++) {
                    sum += dist[j][i];
                }

                if (min > sum) {
                    min = sum;
                    minIdx = i;
                } else if (min == sum) {
                    minIdx = Math.min(minIdx, i);
                }
            }

            sb.append(minIdx).append("\n");
        }

        System.out.println(sb);
    }

    static void dijk(int start, int idx) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();

        boolean[] visited = new boolean[N+1];

        PQ.add(new Node(start, 0));
        visited[start] = true;
        dist[idx][start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            visited[now.dest] = true;

            for (Node next : adj.get(now.dest)) {
                if (!visited[next.dest] && dist[idx][next.dest] > dist[idx][now.dest] + next.len) {
                    dist[idx][next.dest] = dist[idx][now.dest] + next.len;
                    PQ.add(new Node(next.dest, dist[idx][next.dest]));
                }
            }
        }
    }
}