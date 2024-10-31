import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long answer;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new long[N+1][10];

        Arrays.fill(dp[1], 1);
        for (int i = 0 ; i <= N ; i++)
            dp[i][0] = 1;

        solve();

        System.out.println(answer % 10_007);
    }

    static void solve() {
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 1 ; j <= 9 ; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                dp[i][j] %= 10_007;
            }
        }

        for (int i = 0 ; i <= 9 ; i++) {
            answer += dp[N][i];
        }
    }
}