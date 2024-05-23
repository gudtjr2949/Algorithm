import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] dp, trace;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        dp = new int[N+1];
        trace = new int[N+1];

        Arrays.fill(dp, 100_000_000);

        solve();
    }

    static void solve() {
        dp[1] = 0;

        for (int i = 2 ; i <= N ; i++) {
            if (i % 3 == 0 &&  dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }

            if (i-1 > 0 && dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                trace[i] = i - 1;
            }
        }

        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();

        while(N > 0){
            sb.append(N).append(" ");
            N = trace[N];
        }

        System.out.println(sb);
    }
}