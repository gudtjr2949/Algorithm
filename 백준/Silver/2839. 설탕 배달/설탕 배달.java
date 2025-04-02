import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        if (N >= 3) dp[3] = 1;
        if (N >= 5) dp[5] = 1;

        for (int i = 6 ; i <= N ; i++) {
            if (dp[i-3] == 0 && dp[i-5] == 0) {
                dp[i] = 0;
            } else if (dp[i-3] == 0 && dp[i-5] != 0) {
                dp[i] = dp[i-5]+1;
            } else if (dp[i-3] != 0 && dp[i-5] == 0) {
                dp[i] = dp[i-3]+1;
            } else if (dp[i-3] != 0 && dp[i-5] != 0) {
                dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
            }
        }

        if (dp[N] == 0) answer = -1;
        else answer = dp[N];
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
    }

    static void init() {
        dp = new int[N+1];
    }
}