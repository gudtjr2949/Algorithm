import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, from, to, answer, MAX;
    static List<List<Node>> adj;
    static class Node {
        int dest, weight;
        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Node(to, weight));
            adj.get(to).add(new Node(from, weight));
            MAX = Math.max(MAX, weight);
        }

        st = new StringTokenizer(bf.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = MAX;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (bfs(mid)) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        answer = right;
    }

    static boolean bfs(int mid) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(from);
        boolean[] visited = new boolean[N+1];
        visited[from] = true;

        while (!Q.isEmpty()) {
            int now = Q.poll();

            if (now == to) {
                return true;
            }

            for (Node next : adj.get(now)) {
                if (next.weight >= mid && !visited[next.dest]) {
                    visited[next.dest] = true;
                    Q.add(next.dest);
                }
            }
        }

        return false;
    }
}