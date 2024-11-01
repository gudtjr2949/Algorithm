import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new int[N+1][3];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            dp[i][0] = Integer.parseInt(st.nextToken());
            dp[i][1] = Integer.parseInt(st.nextToken());
            dp[i][2] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            dp[i][0] = Math.min(dp[i][0] + dp[i-1][1], dp[i][0] + dp[i-1][2]);
            dp[i][1] = Math.min(dp[i][1] + dp[i-1][0], dp[i][1] + dp[i-1][2]);
            dp[i][2] = Math.min(dp[i][2] + dp[i-1][0], dp[i][2] + dp[i-1][1]);
        }

        for (int i = 0 ; i < 3 ; i++)
            answer = Math.min(answer, dp[N][i]);
    }
}