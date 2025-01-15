import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M, P;
    static String s1, s2, s3;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][M][P]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                for (int k = 1 ; k <= P ; k++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(k-1)) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s1 = bf.readLine();
        s2 = bf.readLine();
        s3 = bf.readLine();
        N = s1.length();
        M = s2.length();
        P = s3.length();
        dp = new int[N+1][M+1][P+1];
    }
}