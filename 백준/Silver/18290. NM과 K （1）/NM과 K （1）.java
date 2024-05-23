import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer = Integer.MIN_VALUE;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int idx, int sum) {
        if (idx == K) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
//                if (j == x && i == y) continue;

                if (!visited[i][j] && check(j, i)) {
                    visited[i][j] = true;
                    dfs(j, i, idx+1, sum + map[i][j]);
                    visited[i][j] = false;
                }
            }
        }
    }

    static boolean check(int nextX, int nextY) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = nextX + dx[i];
            int ny = nextY + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N && visited[ny][nx]) {
                return false;
            }
        }

        return true;
    }
}