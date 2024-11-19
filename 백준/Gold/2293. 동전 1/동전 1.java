import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[K+1];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        dp[0] = 1;

        solve();

        System.out.println(dp[K]);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = arr[i] ; j <= K ; j++) {
                dp[j] = dp[j] + dp[j-arr[i]];
            }
        }
    }
}