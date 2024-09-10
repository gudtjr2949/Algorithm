import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static boolean flag;
    static int[] dp;
    static long[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        dp = new int[N];
        A = new long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            A[i] = Long.parseLong(st.nextToken());
            dp[i] = -1;
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = (long)(N-1) * (1 + Math.abs(A[N-1]-A[0]));

        while (left <= right) {
            dp = new int[N];
            dp[0] = 1;
            flag = false;
            long mid = (left + right) / 2;
            dfs(0, mid);

            if (dp[N-1] == 1) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
    }

    static void dfs(int start, long K) {
        if (start == N-1) {
            flag = true;
            return;
        }

        for (int i = start+1 ; i < N ; i++) {
            long power = (long) (i-start) * (1 + Math.abs(A[i] - A[start]));
            if (power <= K && dp[i] == 0) {
                dp[i] = 1;
                dfs(i, K);
            }
        }
    }
}