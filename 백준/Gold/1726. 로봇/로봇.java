import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M, N, startX, startY, startDir, endX, endY, endDir, answer = 0;
    static int[] dx = {0, 1, -1, 0, 0}, dy = {0, 0, 0, 1, -1};
    static int[][] ddir = {{0, 0}, {4, 3}, {3, 4}, {1, 2}, {2, 1}};
    static int[][] map;

    static class Robot {
        int x, y, dir, time;

        public Robot(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        startY = Integer.parseInt(st.nextToken()) - 1;
        startX = Integer.parseInt(st.nextToken()) - 1;
        startDir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        endY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;
        endDir = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        boolean[][][] visited = new boolean[5][M][N];
        Queue<Robot> Q = new LinkedList<>();
        Q.add(new Robot(startX, startY, startDir, 0));
        visited[startDir][startY][startX] = true;

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int j = 0 ; j < size ; j++) {
                Robot r = Q.poll();

                if (r.x == endX && r.y == endY && r.dir == endDir) {
                    answer = r.time;
                    return;
                }

                // 명령 1
                for (int i = 1; i <= 3; i++) {
                    int nx = r.x + dx[r.dir] * i;
                    int ny = r.y + dy[r.dir] * i;

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[r.dir][ny][nx]) {
                        if (map[ny][nx] == 1) break;
                        visited[r.dir][ny][nx] = true;
                        Q.add(new Robot(nx, ny, r.dir, r.time + 1));
                    }
                }

                // 명령 2
                for (int i = 0; i < 2; i++) {
                    if (!visited[ddir[r.dir][i]][r.y][r.x]) {
                        visited[ddir[r.dir][i]][r.y][r.x] = true;
                        Q.add(new Robot(r.x, r.y, ddir[r.dir][i], r.time + 1));
                    }
                }
            }
        }
    }
}