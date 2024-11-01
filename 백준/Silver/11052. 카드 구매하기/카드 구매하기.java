import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        dp = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }

        solve();

        System.out.println(dp[N]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= i ; j++) {
                dp[i] = Math.max(dp[i], dp[j] + arr[i-j]);
            }
        }
    }
}