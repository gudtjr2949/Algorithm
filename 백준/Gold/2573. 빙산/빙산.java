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

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (true) {
//            print();

            visited = new boolean[N][M];
            int cnt = 0;
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(j, i);
                        cnt++;
                    }
                }
            }

            if (cnt >= 2) break;

            if (cnt == 0) {
                answer = 0;
                break;
            }

            melting();

            answer++;
        }
    }

    private static void print() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void dfs(int x, int y) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] != 0 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }

    static void melting() {
        int[][] tmp = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) {
                    int cnt = 0;
                    for (int k = 0 ; k < 4 ; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 0) cnt++;
                    }
                    tmp[i][j] = cnt;
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
               if (tmp[i][j] != 0) {
                   map[i][j] -= tmp[i][j];
                   if (map[i][j] < 0) map[i][j] = 0;
               }
            }
        }
    }
}