import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());
        dp = new long[N+1][N+1];
        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[from][to] = Math.min(dp[from][to], cost);
        }

        solve();

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) System.out.print(0 + " ");
                else System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i == j) continue;
                    if (dp[i][k] + dp[k][j] < dp[i][j]) dp[i][j] = dp[i][k] + dp[k][j];
                }
            }
        }
    }
}