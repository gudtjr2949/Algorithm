import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M, N, min;
    static boolean[] visited;
    static List<List<Node>> tree;
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            min = 0;
            
            if (M == 0 && N == 0) break;

            tree = new ArrayList<>();
            for (int i = 0; i < M; i++) tree.add(new ArrayList<>());

            visited = new boolean[M];

            int total = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                tree.get(from).add(new Node(to, cost));
                tree.get(to).add(new Node(from, cost));
                total += cost;
            }

            solve();

            System.out.println(total - min);
        }
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        PQ.add(new Node(0, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;
            min += now.cost;

            for (Node next : tree.get(now.idx)) {
                if (!visited[next.idx]) {
                    PQ.add(next);
                }
            }
        }

    }
}