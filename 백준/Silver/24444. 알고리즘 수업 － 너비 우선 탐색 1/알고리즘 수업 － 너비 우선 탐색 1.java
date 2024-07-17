import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[] answer;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        answer = new int[N+1];

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

        bfs();

        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i <= N ; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs() {
        int idx = 1;
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        visited[R] = true;
        Q.add(R);

        while (!Q.isEmpty()) {
            int now = Q.poll();

            answer[now] = idx++;

            for (Integer next : adj.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    Q.add(next);
                }
            }
        }

    }
}