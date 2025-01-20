import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= i / 2 ; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
            answer = Math.max(answer, dp[i]);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }
    }

    static void init() {
        arr = new int[N+1];
        dp = new int[N+1];
    }
}