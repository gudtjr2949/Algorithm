import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, MOD = 9901;;
    static long answer;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        printAnswer();
    }

    static void solve() {
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2 ; i <= N ; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }
    }

    static void printAnswer() {
        answer = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
        System.out.println(answer);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
    }

    static void init() {
        dp = new long[N+1][3];
    }
}