import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, idx;
    static int[] arr;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++)
            adj.add(new ArrayList<>());

        arr = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        for (int i = 1 ; i <= N ; i++)
            Collections.sort(adj.get(i), Collections.reverseOrder());

        visited[start] = true;
        arr[start] = ++idx;

        dfs(start);

        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i <= N ; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cur) {
        if (idx == N) {
            return;
        }

        for (Integer next : adj.get(cur)) {
            if (!visited[next]) {
                visited[next] = true;
                arr[next] = ++idx;
                dfs(next);
            }
        }
    }
}