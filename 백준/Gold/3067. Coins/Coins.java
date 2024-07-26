import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());

            coins = new int[N+1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 1 ; i <= N ; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(bf.readLine());

            dp = new int[N+1][M+1];

            for (int i = 0 ; i <= N ; i++)
                dp[i][0] = 1;

            solve();

            sb.append(dp[N][M]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] = dp[i-1][j];

                if (j - coins[i] >= 0)
                    dp[i][j] += dp[i][j-coins[i]];
            }
        }
    }
}