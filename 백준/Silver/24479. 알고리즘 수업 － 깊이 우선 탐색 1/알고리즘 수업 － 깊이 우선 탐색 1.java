import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R, idx = 1;
    static int[] visited;
    static List<List<Integer>> adj;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new int[N+1];

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++)
            adj.add(new ArrayList<>());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        for (int i = 1 ; i <= N ; i++)
            Collections.sort(adj.get(i));

        visited[R] = 1;

        dfs(R);

        for (int i = 1; i <= N ; i++) {
            answer.append(visited[i]).append("\n");
        }

        System.out.println(answer);
    }

    static void dfs(int cur) {
        if (idx == N) {
            return;
        }

        for (Integer next : adj.get(cur)) {
            if (visited[next] == 0) {
                visited[next] = ++idx;
                dfs(next);
            }
        }
    }
}