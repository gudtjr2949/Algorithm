import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] = map[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        for (int i1 = 1 ; i1 <= N ; i1++) {
            for (int j1 = 1 ; j1 <= M ; j1++) {
                for (int i2 = i1 ; i2 <= N ; i2++) {
                    for (int j2 = j1 ; j2 <= M ; j2++) {
                        answer = Math.max(answer, dp[i2][j2] - dp[i2][j1-1] - dp[i1-1][j2] + dp[i1-1][j1-1]);
                    }
                }
            }
        }

    }
}