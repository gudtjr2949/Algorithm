import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>();

            for (int i = 0 ; i <= N ; i++) {
                adj.add(new ArrayList<>());
            }

            visited = new boolean[N+1];

            for (int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adj.get(from).add(to);
                adj.get(to).add(from);
            }

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs() {
        Queue<Integer> Q = new LinkedList<>();
        int cnt = 0;

        Q.add(1);
        visited[1] = true;

        while (!Q.isEmpty()) {
            int now = Q.poll();

            for (int next : adj.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    Q.add(next);
                }
            }
        }

        return cnt;
    }
}