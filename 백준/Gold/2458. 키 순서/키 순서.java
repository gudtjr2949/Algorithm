import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = 0;
    static int[] cnt;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        cnt = new int[N+1];
        dp = new boolean[N+1][N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            dp[small][big] = true;
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (dp[i][k] && dp[k][j]) dp[i][j] = true;
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (dp[i][j] || dp[j][i]) {
                    cnt[i]++;
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (cnt[i] == N-1) answer++;
        }
    }
}