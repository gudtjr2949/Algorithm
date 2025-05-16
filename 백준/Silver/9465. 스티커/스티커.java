import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            input(bf);
            solve();
            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.println(sb);
    }

    static void solve() {
        dp[0][1] = arr[0][1];
        dp[1][1] = arr[1][1];

        for (int j = 2 ; j <= N ; j++) {
            dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
            dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
        }
    }

    static void input(BufferedReader bf) throws Exception {
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st1 = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[0][i] = Integer.parseInt(st1.nextToken());
            arr[1][i] = Integer.parseInt(st2.nextToken());
        }
    }

    static void init() {
        arr = new int[2][N+1];
        dp = new int[2][N+1];
    }
}