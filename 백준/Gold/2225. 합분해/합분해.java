import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K+1][N+1];
        solve();

        System.out.println(dp[K][N]);
    }

    static void solve() {
        dp[0][0] = 1;

        for (int i = 1 ; i <= K ; i++) {
            for (int j = 0 ; j <= N ; j++) {
                for (int l = 0 ; l <= j ; l++) {
                    dp[i][j] += dp[i-1][l];
                    dp[i][j] %= 1_000_000_000;
                }
            }
        }
    }
}