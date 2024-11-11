import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static boolean possible;
    static int[] input;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(bf.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            input = new int[V+1];
            visited = new boolean[V+1];
            adj = new ArrayList<>();
            possible = true;

            for (int i = 0 ; i <= V ; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0 ; i < E ; i++) {
                st = new StringTokenizer(bf.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            for (int i = 1 ; i <= V ; i++) {
                if (input[i] == 0) {
                    input[i] = 1;
                    dfs(i, 1);
                }
            }

            if (possible) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int now, int color) {
        if (!possible) return;

        for (int next : adj.get(now)) {
            if (input[next] == color) {
                possible = false;
                return;
            }

            if (!visited[next] && input[next] != color) {
                visited[next] = true;
                input[next] = color * -1;
                dfs(next, color * -1);
            }
        }
    }
}