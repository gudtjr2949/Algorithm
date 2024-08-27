import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        sb = new StringBuilder();
        map = new int[N][3];
        dp = new int[2][N][3];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < 3 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = map[0][0];
        dp[0][0][1] = map[0][1];
        dp[0][0][2] = map[0][2];
        dp[1][0][0] = map[0][0];
        dp[1][0][1] = map[0][1];
        dp[1][0][2] = map[0][2];

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i < N ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                switch (j) {
                    case 0 :
                        dp[0][i][j] = map[i][j] + Math.max(dp[0][i-1][j], dp[0][i-1][j+1]);
                        dp[1][i][j] = map[i][j] + Math.min(dp[1][i-1][j], dp[1][i-1][j+1]);
                        break;
                    case 1 :
                        dp[0][i][j] = map[i][j] + Math.max(dp[0][i-1][j-1], Math.max(dp[0][i-1][j], dp[0][i-1][j+1]));
                        dp[1][i][j] = map[i][j] + Math.min(dp[1][i-1][j-1], Math.min(dp[1][i-1][j], dp[1][i-1][j+1]));
                        break;
                    case 2 :
                        dp[0][i][j] = map[i][j] + Math.max(dp[0][i-1][j-1], dp[0][i-1][j]);
                        dp[1][i][j] = map[i][j] + Math.min(dp[1][i-1][j-1], dp[1][i-1][j]);
                        break;
                }
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0 ; i < 3 ; i++) {
            max = Math.max(max, dp[0][N-1][i]);
            min = Math.min(min, dp[1][N-1][i]);
        }

        sb.append(max).append(" ").append(min);
    }
}