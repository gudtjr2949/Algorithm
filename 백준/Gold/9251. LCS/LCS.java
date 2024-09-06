import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M;
    static int[][] dp;
    static String s1, s2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s1 = bf.readLine();
        s2 = bf.readLine();

        solve();

        System.out.println(dp[N][M]);
    }

    static void solve() {
        N = s1.length();
        M = s2.length();

        dp = new int[N+1][M+1];

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }
}