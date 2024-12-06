import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        dp = new int[N+1];

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        solve();

        System.out.println(dp[N]);
    }

    static void solve() {
        dp[0] = 0;
        dp[1] = arr[1];

        if (N > 1)
            dp[2] = arr[1] + arr[2];

        for (int i = 3 ; i <= N ; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i];
        }
    }
}