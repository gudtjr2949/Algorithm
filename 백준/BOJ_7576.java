package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 7576 : 토마토
public class BOJ_7576 {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    static Queue<Point> Q = new LinkedList<>();

    static int cnt = -1;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nm = bf.readLine();
        String[] nm_arr = nm.split(" ");

        M = Integer.parseInt(nm_arr[0]);
        N = Integer.parseInt(nm_arr[1]);

        board = new int[N][M];

        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(s_arr[j]);

                if (board[i][j] == 1) {
                    Q.add(new Point(j, i));
                }
            }
        }

        bfs();

        check();

        System.out.println(cnt);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs() {
        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();

                visited[p.y][p.x] = true;
                board[p.y][p.x] = 1;

                for (int j = 0; j < 4; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx] && board[dy][dx] == 0) {
                        visited[dy][dx] = true;
                        board[dy][dx] = 1;
                        Q.add(new Point(dx, dy));
                    }
                }
            }

            cnt++;
        }
    }

    private static void check() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (board[i][j] == 0){
                    cnt = -1;
                    return;
                }
            }
        }
    }
}
