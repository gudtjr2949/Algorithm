import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
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

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (!check()) {
            visited = new boolean[N][M];

            // 1. 외부 공기 체크
            dfs(0, 0);

            // 2. 치즈 녹이기
            meltedCheese();

            answer++;
        }

        System.out.println(answer);

    }

    static void meltedCheese() {
        for (int i = 1 ; i < N-1 ; i++) {
            for (int j = 1 ; j < M-1 ; j++) {
                if (map[i][j] == 1) {
                    int cnt = 0;
                    for (int q = 0 ; q < 4 ; q++) {
                        int dx = j + nx[q];
                        int dy = i + ny[q];

                        if (visited[dy][dx]) cnt++;
                    }

                    if (cnt >= 2) map[i][j] = 0;
                }
            }
        }
    }

    static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx] && map[dy][dx] == 0) {
                visited[dy][dx] = true;
                dfs(dx, dy);
            }
        }
    }

    static boolean check() {
        for (int i = 1 ; i < N-1 ; i++) {
            for (int j = 1 ; j < M-1 ; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}