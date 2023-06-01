package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// SWEA 1861 : 정사각형 방
public class SWEA_1861 {
    static int N;
    static int[][] board;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static int max_cnt;
    static int idx;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {

            N = Integer.parseInt(bf.readLine());

            board = new int[N][N];

            max_cnt = 0;

            idx = 0;

            for (int j = 0; j < N; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                for (int q = 0; q < N; q++) {
                    board[j][q] = Integer.parseInt(s_arr[q]);
                }
            }

            for (int j = 0; j < N; j++) {
                for (int q = 0; q < N; q++) {
                    bfs(q, j); // 모든 시작점의 경우의 수 탐색
                }
            }

            sb.append("#").append(i + 1).append(" ").append(idx).append(" ").append(max_cnt).append("\n");
        }
        System.out.println(sb);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int x, int y) {
        int cnt = 1;

        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y));

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {

                Point p = Q.poll();

                for (int j = 0; j < 4; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && board[p.y][p.x] + 1 == board[dy][dx]) {
                        Q.add(new Point(dx, dy));
                        cnt++;
                    }
                }
            }
        }

        if (max_cnt == cnt) {
            max_cnt = cnt;
            idx = Math.min(idx, board[y][x]);
        } else if (max_cnt < cnt) {
            max_cnt = cnt;
            idx = board[y][x];
        }
    }
}