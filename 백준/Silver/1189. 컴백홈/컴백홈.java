import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        input();
        visited[R-1][0] = true;
        dfs(0, R-1, 1);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt) {
        if (x == C-1 && y == 0) {
            if (cnt == K) answer++;
            return;
        }

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] != 'T') {
                visited[ny][nx] = true;
                dfs(nx, ny, cnt+1);
                visited[ny][nx] = false;
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }

    static void init() {
        map = new char[R][C];
        visited = new boolean[R][C];
    }
}