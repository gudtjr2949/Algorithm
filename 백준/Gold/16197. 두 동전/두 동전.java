import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, x1, y1, x2, y2;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static class Coin {
        int x1, y1, x2, y2, time;

        public Coin(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Coin{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        boolean find = false;

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') {
                    if (!find) {
                        x1 = j;
                        y1 = i;
                        find = true;
                    } else {
                        x2 = j;
                        y2 = i;
                    }
                }
            }
        }

        bfs();
    }
    static void bfs() {
        Queue<Coin> Q = new LinkedList<>();
        Q.add(new Coin(x1, y1, x2, y2, 0));
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[y1][x1][y2][x2] = true;

        while (!Q.isEmpty()) {
            Coin c = Q.poll();

            if (c.time >= 10) {
                System.out.println(-1);
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx1 = c.x1 + dx[i];
                int ny1 = c.y1 + dy[i];
                int nx2 = c.x2 + dx[i];
                int ny2 = c.y2 + dy[i];

                // 벽 체크
                if (!canMove(nx1, ny1)) {
                    ny1 = c.y1;
                    nx1 = c.x1;
                }
                if (!canMove(nx2, ny2)) {
                    ny2 = c.y2;
                    nx2 = c.x2;
                }

                int cnt = 0;
                if (nx1 >= 0 && nx1 < M && ny1 >= 0 && ny1 < N) cnt++;
                if (nx2 >= 0 && nx2 < M && ny2 >= 0 && ny2 < N) cnt++;

                // 하나만 나가는 경우
                if (cnt == 1) {
                    System.out.println(c.time+1);
                    return;
                }
                // 둘 다 안나가는 경우
                else if (cnt == 2 && !visited[ny1][nx1][ny2][nx2]) {
                    visited[ny1][nx1][ny2][nx2] = true;
                    Q.add(new Coin(nx1, ny1, nx2, ny2, c.time+1));
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean canMove(int nx, int ny) {
        if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == '#') {
            return false;
        }
        return true;
    }
}