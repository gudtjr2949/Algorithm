import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        dp = new int[M+1][N+1];

        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= M ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (map[i][j] == 0) {
                    if (map[i-1][j] == 0 && map[i][j-1] == 0 && map[i-1][j-1] == 0) {
                        int min = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                        dp[i][j] = min + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 1 ; i <= M ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
    }
}