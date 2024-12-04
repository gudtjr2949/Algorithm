import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int cnt = countMountain();
            if (cnt == 0) {
                answer = 0;
                break;
            } else if (cnt >= 2) {
                break;
            }

            melt();
            answer++;
        }

        System.out.println(answer);
    }

    static void melt() {
        int[][] tmp = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0 ; k < 4 ; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (!isRange(nx, ny)) continue;

                        if (map[ny][nx] == 0) tmp[i][j]++;
                    }
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (tmp[i][j] != 0) {
                    map[i][j] -= tmp[i][j];
                    if (map[i][j] < 0 ) map[i][j] = 0;
                }
            }
        }
    }

    static int countMountain() {
        visited = new boolean[N][M];

        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(j, i);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] != 0) {
                dfs(nx, ny);
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}