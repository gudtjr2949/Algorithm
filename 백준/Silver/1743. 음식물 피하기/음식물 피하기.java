import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] nx = {0, 1, 0, -1}, ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            map[y][x] = 1;
        }

        int max = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    cnt = 1;
                    dfs(j, i);
                    max = Math.max(max, cnt);
                }
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y) {

        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx] && map[dy][dx] == 1) {
                visited[dy][dx] = true;
                cnt++;
                dfs(dx, dy);
            }
        }
    }
}