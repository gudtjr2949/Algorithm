package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// SWEA 1953 : 탈주범 검거
public class SWEA_1953 {

    static int N;
    static int M;
    static int Y;
    static int X;
    static int L;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;

    static int[] nx = { 0, 1, 0, -1 };
    static int[] ny = { -1, 0, 1, 0 };

    static int[][] next_dir = { { 1, 2, 5, 6 }, { 1, 3, 6, 7 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 } };

    static int[][] cur_dir = { { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, { 1, 2, 5, 6 }, { 1, 3, 6, 7 } };

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            N = Integer.parseInt(s_arr[0]);
            M = Integer.parseInt(s_arr[1]);
            Y = Integer.parseInt(s_arr[2]);
            X = Integer.parseInt(s_arr[3]);
            L = Integer.parseInt(s_arr[4]);
            map = new int[N][M];
            visited = new boolean[N][M];
            cnt = 1;

            for (int j = 0; j < N; j++) {
                String s2 = bf.readLine();
                String[] s2_arr = s2.split(" ");

                for (int q = 0; q < M; q++) {
                    map[j][q] = Integer.parseInt(s2_arr[q]);
                }
            }

            bfs();

            sb.append("#").append(i + 1).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(X, Y, 1));

        int depth = 1;

        while (!Q.isEmpty() && depth < L) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();

                visited[p.y][p.x] = true;

                for (int j = 0; j < 4; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx] && check(j, p.x, p.y, dx, dy) && map[dy][dx] != 0) {
                        visited[dy][dx] = true;
                        Q.add(new Point(dx, dy, p.depth + 1));
                        cnt++;
                    }
                }
                depth = p.depth + 1;
            }

        }
    }

    private static boolean check(int idx, int x, int y, int next_x, int next_y) {
        boolean now = false;
        boolean next = false;

        for (int i = 0; i < 4; i++) {
            if (cur_dir[idx][i] == map[y][x]) {
                now = true;
            }

            if (next_dir[idx][i] == map[next_y][next_x]) {
                next = true;
            }
        }

        if (now && next)
            return true;
        else {
            return false;
        }
    }

    static class Point {
        int x;
        int y;
        int depth;

        public Point(int x, int y, int depth) {
            super();
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

    }

}

