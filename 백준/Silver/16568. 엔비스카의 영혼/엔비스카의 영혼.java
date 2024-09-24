import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, A, B;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        Arrays.fill(dp, 1_000_001);
        dp[N] = 0;

        solve();

        System.out.println(dp[0]);
    }

    static void solve() {
        for (int i = N ; i >= 0 ; i--) {
            if (i > 0) dp[i-1] = Math.min(dp[i-1], dp[i]+1);
            if (i-A-1 >= 0) dp[i-A-1] = Math.min(dp[i-A-1], dp[i]+1);
            if (i-B-1 >= 0) dp[i-B-1] = Math.min(dp[i-B-1], dp[i]+1);
        }
    }
}