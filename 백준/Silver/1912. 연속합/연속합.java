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
        answer = arr[0];
        dp[0] = arr[0];

        for (int i = 1 ; i < N ; i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
            answer = Math.max(answer, dp[i]);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(st.nextToken());
    }

    static void init() {
        arr = new int[N];
        dp = new int[N];
    }
}