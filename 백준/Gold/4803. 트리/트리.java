import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean cycle;
    static boolean[] visited, finished;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int idx = 0;

        while (true) {
            idx++;

            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            visited = new boolean[N+1];
            finished = new boolean[N+1];

            adj = new ArrayList<>();

            for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

            for (int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj.get(from).add(to);
                adj.get(to).add(from);
            }

            int cnt = 0;

            for (int i = 1 ; i <= N ; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    cycle = false;
                    dfs(i);
                    if (!cycle) cnt++;
                }
            }

            sb.append("Case " + idx + ": ");

            if (cnt > 1) {
                sb.append("A forest of " + cnt +" trees.");
            } else if (cnt == 1) {
                 sb.append("There is one tree.");
            } else {
                sb.append("No trees.");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int now) {

        for (Integer next : adj.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            } else {
                if (finished[next]) cycle = true; // 다음에 방문할 노드에 이미 방문한 경우
            }
        }

        finished[now] = true; // now 노드의 가능한 모든 노드를 탐색함
    }
}