import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, T;
    static int[][] arr, dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][T]);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= T  ; j++) {
                if (arr[i][0] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j-arr[i][0]] + arr[i][1], dp[i-1][j]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            arr[i][0] = K;
            arr[i][1] = S;
        }
    }

    static void init() {
        dp = new int[N+1][T+1];
        arr = new int[N+1][2];
    }
}