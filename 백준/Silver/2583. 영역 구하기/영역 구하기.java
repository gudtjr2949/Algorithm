import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
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

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1 ; j < y2 ; j++) {
                for (int q = x1 ; q < x2 ; q++) {
                    map[j][q] = -1;
                    visited[j][q] = true;
                }
            }
        }

        int cnt = 1;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 0) {
                    dfs(j, i, cnt);
                    cnt++;
                }
            }
        }

        int[] size = new int[cnt];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] > 0) {
                    size[map[i][j]]++;
                }
            }
        }

        Arrays.sort(size);

        System.out.println(cnt-1);

        for (int i = 1 ; i < cnt ; i++) {
            System.out.print(size[i] + " ");
        }

    }

    static void dfs(int x, int y, int num) {

        map[y][x] = num;
        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx] && map[dy][dx] == 0) {
                visited[dy][dx] = true;
                map[dy][dx] = num;
                dfs(dx, dy, num);
            }
        }
    }
}