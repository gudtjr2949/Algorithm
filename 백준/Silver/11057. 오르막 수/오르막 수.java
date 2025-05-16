import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long answer;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        findAnswer();
        System.out.println(answer%10_007);
    }

    static void findAnswer() {
        for (int i = 0 ; i < 10 ; i++) {
            answer += dp[N][i];
        }
    }

    static void solve() {
        for (int i = 2 ; i <= N ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                for (int k = 0 ; k <= j ; k++) {
                    dp[i][j] += dp[i-1][k];
                }
                dp[i][j] %= 10_007;
            }
        }

    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
    }

    static void init() {
        dp = new long[N+1][10];
        Arrays.fill(dp[1], 1);
    }
}