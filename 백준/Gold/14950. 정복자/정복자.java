import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, T, answer;
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
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        PQ.add(new Node(1, 0));
        int cnt = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.idx]) continue;

            answer += now.cost;
            visited[now.idx] = true;
            cnt++;

            if(cnt > 2){
                answer += T * (cnt-2);
            }

            for (Node next : adj.get(now.idx)) {
                if (!visited[next.idx]) {
                    PQ.add(next);
                }
            }
        }
    }
}