import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static long answer = Integer.MAX_VALUE;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dp = new long[V+1][V+1];

        for (int i = 0 ; i <= V ; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[from][to] = cost;
        }

        solve();
        
        
        if (answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
    }

    static void solve() {
        for (int k = 1 ; k <= V ; k++) {
            for (int i = 1 ; i <= V ; i++) {
                for (int j = 1 ; j <= V ; j++) {
                    if (i == j) continue;

                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        for (int i = 1 ; i <= V ; i++) {
            for (int j = 1 ; j <= V ; j++) {
                if (i == j) continue;

                answer = Math.min(answer, dp[i][j] + dp[j][i]);
            }
        }
    }
}