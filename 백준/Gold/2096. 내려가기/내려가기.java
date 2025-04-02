import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, min, max;
    static int[][] map, maxDP, minDP;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(max + " " + min);
    }

    static void solve() {
        for (int i = 0 ; i < 3 ; i++) {
            maxDP[0][i] = map[0][i];
            minDP[0][i] = map[0][i];
        }

        for (int i = 1 ; i < N ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if (j == 0) {
                    maxDP[i][j] = Math.max(maxDP[i-1][0], maxDP[i-1][1]) + map[i][j];
                    minDP[i][j] = Math.min(minDP[i-1][0], minDP[i-1][1]) + map[i][j];
                } else if (j == 1) {
                    maxDP[i][j] = Math.max(maxDP[i-1][0], Math.max(maxDP[i-1][1], maxDP[i-1][2])) + map[i][j];
                    minDP[i][j] = Math.min(minDP[i-1][0], Math.min(minDP[i-1][1], minDP[i-1][2])) + map[i][j];
                } else {
                    maxDP[i][j] = Math.max(maxDP[i-1][1], maxDP[i-1][2]) + map[i][j];
                    minDP[i][j] = Math.min(minDP[i-1][1], minDP[i-1][2]) + map[i][j];
                }
            }
        }

        min = Integer.MAX_VALUE;
        max = 0;

        for (int i = 0 ; i < 3 ; i++) {
            min = Math.min(min, minDP[N-1][i]);
            max = Math.max(max, maxDP[N-1][i]);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < 3 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        map = new int[N][3];
        maxDP = new int[N][3];
        minDP = new int[N][3];
    }
}