import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dp;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        dp = new int[N+1][2];
        visited = new boolean[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int now) {
        visited[now] = true;
        dp[now][0] = 0;
        dp[now][1] = 1;

        for (int next : adj.get(now)) {
            if (!visited[next]) {
                dfs(next);
                dp[now][0] += dp[next][1];
                dp[now][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}