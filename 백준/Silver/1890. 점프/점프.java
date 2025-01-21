import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N-1][N-1]);
    }

    static void solve() {
        dp[0][0] = 1;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                int jump = map[i][j];
                if (map[i][j] == 0) break;

                int downX = j;
                int downY = i + jump;
                int rightX = j + jump;
                int rightY = i;

                if (isRange(downX, downY)) dp[downY][downX] += dp[i][j];
                if (isRange(rightX, rightY)) dp[rightY][rightX] += dp[i][j];
            }
        }

    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        map = new int[N][N];
        dp = new long[N][N];
    }
}