import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M, length;
    static String s1, s2;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s1 = bf.readLine();
        s2 = bf.readLine();

        N = s1.length();
        M = s2.length();

        dp = new int[N+1][M+1];

        solve();

        System.out.println(length);
        System.out.println(sb.reverse());
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

                length = Math.max(length, dp[i][j]);
            }
        }


        // findAnswer
        while (N > 0 && M > 0) {
            if (s1.charAt(N-1) == s2.charAt(M-1)) {
                sb.append(s1.charAt(N-1));
                N--;
                M--;
            } else if (dp[N][M] == dp[N-1][M]) {
                N--;
            } else if (dp[N][M] == dp[N][M-1]) {
                M--;
            }
        }
    }
}