import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        A = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        int answer = -1;

        for (int i = 0 ; i < N ; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    static void solve() {
        dp[0] = A[0];
        for (int i = 1; i < N; i++) {
            dp[i] = A[i];
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
        }
    }
}