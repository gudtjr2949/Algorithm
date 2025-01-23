import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer, idx;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] visited;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        idx = 1;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (visited[i][j] == 0) {
                    dfs(j, i);
                }
            }
        }
    }

    static void dfs(int x, int y) {
        visited[y][x] = idx;

        int dir = dirToInt(map[y][x]);

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (visited[ny][nx] == 0) {
            dfs(nx, ny);
        } else {
            if (visited[ny][nx] == idx) {
                answer++;
            }

            idx++;
        }
    }

    static int dirToInt(char c) {
        if (c == 'U') return 0;
        else if (c == 'R') return 1;
        else if (c == 'D') return 2;
        else return 3;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }

    static void init() {
        visited = new int[N][M];
        map = new char[N][M];
    }
}