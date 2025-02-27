import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer, cnt, MAX;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited, flooded;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < MAX; i++) { // 온 비 양
            raining(i);
            cnt = 0;
            visited = new boolean[N][N];
            findSafeArea();
            answer = Math.max(answer, cnt);
        }
    }

    static void findSafeArea() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!flooded[i][j] && !visited[i][j]) {
                    cnt++;
                    visited[i][j] = true;
                    dfs(j, i);
                }
            }
        }
    }

    static void dfs(int x, int y) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !flooded[ny][nx] && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void raining(int precipitation) {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j] <= precipitation) {
                    flooded[i][j] = true;
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                MAX = Math.max(MAX, map[i][j]);
            }
        }
    }

    static void init() {
        map = new int[N][N];
        flooded = new boolean[N][N];
    }
}