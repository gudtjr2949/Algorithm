import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] idx;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();

        dp = new int[N+1][M+1];

        for (int i = 0 ; i <= N ; i++)
            Arrays.fill(dp[i], 100_000_000);

        for (int i = 0 ; i <= N ; i++)
            dp[i][i] = 0;

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            dp[A][B] = T;
        }


        K = Integer.parseInt(bf.readLine());
        idx = new int[K+1];
        st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= K ; i++) idx[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i == j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k][i]);
                }
            }
        }


        int[][] dist = new int[K+1][N+1];
        int[] max = new int[N+1];

        for (int i = 1 ; i <= K ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                dist[i][j] = dp[idx[i]][j] + dp[j][idx[i]];
            }
        }

        int min = 100_000_000;

        for (int i = 1 ; i <= N ; i++) {
            int tmp = 0;
            for (int j = 1 ; j <= K ; j++) {
                tmp = Math.max(tmp, dist[j][i]);
            }
            max[i] = tmp;
            min = Math.min(min, max[i]);
        }

        for (int i = 1 ; i <= N ; i++) {
            if (max[i] == min) sb.append(i).append(" ");
        }
    }
}