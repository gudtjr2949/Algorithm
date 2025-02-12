import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M;
    static String A, B;
    static int[][] dp;


    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][M]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        A = bf.readLine();
        B = bf.readLine();
        N = A.length();
        M = B.length();
        init();
    }

    static void init() {
        dp = new int[N+1][M+1];
    }
}