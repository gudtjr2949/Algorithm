import javax.swing.plaf.basic.BasicViewportUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][M]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] += Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        dp = new int[N+1][M+1];
    }
}