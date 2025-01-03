import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static long answer;
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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++) tree.add(new ArrayList<>());
        visited = new boolean[V+1];

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree.get(from).add(new Node(to, cost));
            tree.get(to).add(new Node(from, cost));
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        PQ.add(new Node(1, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;
            answer += now.cost;

            for (Node next : tree.get(now.idx)) {
                if (!visited[next.idx]) {
                    PQ.add(next);
                }
            }
        }
    }
}