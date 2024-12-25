import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S, E, answer;
    static int[] beforeIdx;
    static boolean[] visited;
    static List<List<Integer>> adj;
    static class Node {
        int idx, cnt;
        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
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

        visited = new boolean[N+1];
        beforeIdx = new int[N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        for (int i = 0 ; i <= N ; i++) {
            Collections.sort(adj.get(i));
        }

        st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        bfs(S, E);

        // visited 배열 초기화
        visited = new boolean[N+1];
        int visitedIdx = beforeIdx[E];

        while (visitedIdx != S) {
            visited[visitedIdx] = true;
            visitedIdx = beforeIdx[visitedIdx];
        }

        bfs(E, S);

        System.out.println(answer);
    }

    static void bfs(int start, int end) {
        Queue<Node> Q = new LinkedList<>();

        Q.add(new Node(start, 0));
        visited[start] = true;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            if (now.idx == end) {
                answer += now.cnt;
                return;
            }

            for (int next : adj.get(now.idx)) {
                if (!visited[next]) {
                    visited[next] = true;
                    beforeIdx[next] = now.idx;
                    Q.add(new Node(next, now.cnt+1));
                }
            }
        }
    }
}