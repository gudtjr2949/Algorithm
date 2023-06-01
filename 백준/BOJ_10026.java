package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 10026 : 적록색약
public class BOJ_10026 {

    static int N;
    static char[][] board;
    static char[][] board_tmp; // 색약용 배열

    static boolean[][] visited;
    static boolean[][] visited_tmp; // 색약용 배열

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        board = new char[N][N];
        board_tmp = new char[N][N];

        visited = new boolean[N][N];
        visited_tmp = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();

            for (int j = 0 ; j < N ; j++) {
                board[i][j] = s.charAt(j);

                // 만약 색약용 배열에 G가 들어오는 경우 R로 바꿔서 넣어줌
                if (s.charAt(j) == 'G') {
                    board_tmp[i][j] = 'R';
                }
                else {
                    board_tmp[i][j] = s.charAt(j);
                }
            }
        }

        int cnt = 0;

        int cnt_tmp = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[i][j]) {
                    bfs(j, i);
                    cnt++;
                }

                // 색약용 카운트
                if (!visited_tmp[i][j]) {
                    bfs_tmp(j, i);
                    cnt_tmp++;
                }
            }
        }

        System.out.println(cnt + " " + cnt_tmp);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int x, int y) {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y));

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                Point p = Q.poll();
                visited[p.y][p.x] = true;

                for (int j = 0 ; j < 4 ; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dy >= N || dy < 0 || dx >= N || dx < 0) && !visited[dy][dx] && board[y][x] == board[dy][dx]) {
                        Q.add(new Point(dx, dy));
                        visited[dy][dx] = true;
                    }
                }
            }
        }
    }

    // 색약용 bfs
    private static void bfs_tmp(int x, int y) {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y));

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                Point p = Q.poll();
                visited_tmp[p.y][p.x] = true;

                for (int j = 0 ; j < 4 ; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dy >= N || dy < 0 || dx >= N || dx < 0) && !visited_tmp[dy][dx] && board_tmp[y][x] == board_tmp[dy][dx]) {
                        Q.add(new Point(dx, dy));
                        visited_tmp[dy][dx] = true;
                    }
                }
            }
        }
    }
}
