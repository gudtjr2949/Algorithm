import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, max;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.fill(dp, 1);

        solve();

        System.out.println(N - max);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j <= i ; j++) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            max = Math.max(max, dp[i]);
        }
    }
}