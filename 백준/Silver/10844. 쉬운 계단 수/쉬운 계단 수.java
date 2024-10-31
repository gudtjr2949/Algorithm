import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, MOD = 1_000_000_000;
    static long answer;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new long[N+1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        solve();
        System.out.println(answer % MOD);
    }

    static void solve() {
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 0 ; j <= 9 ; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j+1];
                } else if (j == 9) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
                }
                
                dp[i][j] %= MOD;
            }
        }

        for (int i = 0 ; i <= 9 ; i++) {
            answer += dp[N][i];
        }
    }
}