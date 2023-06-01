package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 17836 : 공주님을 구해라!
public class BOJ_17836 {

    static int N, M, T, answer;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        if (answer != 0 && answer <= T) {
            System.out.println(answer);
        } else {
            System.out.println("Fail");
        }
    }

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    private static void bfs() {
        Queue<Point> Q = new LinkedList<>();

        if (map[0][0] == 2) {
            Q.add(new Point(0, 0, 1, 0));
        } else {
            Q.add(new Point(0, 0, 0, 0));
        }

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();
                int x = p.x;
                int y = p.y;
                int knife = p.knife;
                int depth = p.depth;

                if (x == M - 1 && y == N - 1) {
                    answer = depth;
                    return;
                }

                if (map[y][x] == 2) {
                    knife = 1;
                }

                visited[y][x][knife] = true;

                for (int j = 0; j < 4; j++) {
                    int dx = x + nx[j];
                    int dy = y + ny[j];

                    if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx][knife]) {
                        if (knife == 0 && map[dy][dx] != 1) {
                            visited[dy][dx][knife] = true;
                            Q.add(new Point(dx, dy, knife, depth + 1));
                        } else if (knife == 1) {
                            visited[dy][dx][knife] = true;
                            Q.add(new Point(dx, dy, knife, depth + 1));
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int x, y, knife, depth;

        public Point(int x, int y, int knife, int depth) {
            this.x = x;
            this.y = y;
            this.knife = knife;
            this.depth = depth;
        }
    }
}
