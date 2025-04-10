import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, farNode, maxLength;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node {
        int idx, cost;
        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(maxLength);
    }

    static void solve() {
        dfs(1, 0); // 가장 멀리 있는 노드 찾기

        visited = new boolean[N+1];
        maxLength = 0;
        dfs(farNode, 0);
    }

    static void dfs(int now, int length) {
        if (visited[now]) return;
        visited[now] = true;

        if (maxLength < length) {
            farNode = now;
            maxLength = length;
        }

        for (Node next : adj.get(now)) {
            if (!visited[next.idx]) {
                dfs(next.idx, length + next.cost);
            }
        }
    }


    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, cost));
            adj.get(to).add(new Node(from, cost));
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N+1];
    }
}