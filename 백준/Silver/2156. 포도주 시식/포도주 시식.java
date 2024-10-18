import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        dp = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        if (N == 1) {
            System.out.println(arr[0]);
        } else if (N == 2) {
            System.out.println(arr[0] + arr[1]);
        } else {
            solve();
            System.out.println(dp[N - 1]);
        }
    }

    static void solve() {
        dp[0] = arr[0];
        dp[1] = arr[0] + arr[1];
        dp[2] = Math.max(dp[0] + arr[2], Math.max(arr[1] + arr[2], dp[1]));

        for (int i = 3 ; i < N ; i++) {
            dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], Math.max(dp[i-2] + arr[i], dp[i-1]));
        }

        Arrays.sort(dp);
    }
}