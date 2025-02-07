import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, MAX = 100_000_000;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[K][N] == MAX ? -1 : dp[K][N]);
    }

    static void solve() {
        Arrays.sort(coins);
        for (int i = 1 ; i <= K ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (i < coins[j]) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-coins[j]][j] + 1, dp[i][j-1]);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= N ; i++) coins[i] = Integer.parseInt(bf.readLine());
    }

    static void init() {
        coins = new int[N+1];
        dp = new int[K+1][N+1];
        for (int i = 0 ; i <= K ; i++) Arrays.fill(dp[i], MAX);
        for (int i = 1 ; i <= N ; i++) dp[0][i] = 0;
    }
}