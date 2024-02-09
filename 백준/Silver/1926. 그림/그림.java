import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 0, idx = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

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

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    idx++;
                    dfs(j, i);
                }
            }
        }

        int[] size = new int[idx];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) {
                    size[map[i][j]-1]++;
                }
            }
        }

        Arrays.sort(size);

        if (idx != 0) {
            System.out.println(idx);
            System.out.println(size[idx-1]);
        } else {
            System.out.println(0);
            System.out.println(0);
        }

    }

    static void dfs(int x, int y) {
        visited[y][x] = true;
        map[y][x] = idx;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx] && map[dy][dx] == 1) {
                visited[dy][dx] = true;
                map[dy][dx] = idx;
                dfs(dx, dy);
            }
        }
    }
}