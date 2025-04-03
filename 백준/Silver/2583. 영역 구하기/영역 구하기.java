import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, size;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static List<Integer> list;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(list.size());
        for (int size : list) {
            System.out.print(size + " ");
        }
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    size = 1;
                    visited[i][j] = true;
                    dfs(j, i);
                    list.add(size);
                }
            }
        }

        Collections.sort(list);
    }

    static void dfs(int x, int y) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && map[ny][nx] == 0 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                size++;
                dfs(nx, ny);
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            makeMap(x1, y1, x2, y2);
        }
    }

    static void makeMap(int x1, int y1, int x2, int y2) {
        for (int i = y1 ; i < y2 ; i++) {
            for (int j = x1 ; j < x2 ; j++) {
                map[i][j] = 1;
            }
        }
    }

    static void init() {
        map = new int[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<>();
    }
}