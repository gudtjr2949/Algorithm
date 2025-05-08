import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer, MAX = 500_001;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int min = MAX;

        for (int i = 1 ; i <= N ; i++) {
            int cnt = 0;
            for (int j = 1 ; j <= N ; j++) {
                cnt += map[i][j];
            }

            if (min > cnt) {
                min = cnt;
                answer = i;
            }

        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }
    }

    static void init() {
        map = new int[N+1][N+1];
        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(map[i], MAX);
            map[i][i] = 0;
        }
    }
}