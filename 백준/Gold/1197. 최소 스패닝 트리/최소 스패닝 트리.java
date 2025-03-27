import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static long answer;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node {
        int idx;
        long cost;
        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> (int) o1.cost - (int) o2.cost);
        PQ.add(new Node(1, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;
            answer += now.cost;

            for (Node next : adj.get(now.idx)) {
                if (!visited[next.idx]) {
                    PQ.add(next);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++) adj.add(new ArrayList<>());
        visited = new boolean[V+1];
    }
}