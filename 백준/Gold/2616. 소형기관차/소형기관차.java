import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, prefix;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        makePrefix();
        solve();
        System.out.println(dp[3][N]);
    }

    static void solve() {
        for (int i = 1 ; i <= 3 ; i++) {
            for (int j = M ; j <= N ; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + (prefix[j] - prefix[j-M]));
            }
        }
    }

    static void makePrefix() {
        for (int i = 1 ; i <= N ; i++) {
            prefix[i] = arr[i] + prefix[i-1];
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
    }

    static void init() {
        arr = new int[N+1];
        prefix = new int[N+1];
        dp = new int[4][N+1];
    }
}