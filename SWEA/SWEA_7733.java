package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// SWEA 7733 : 치즈 도둑
public class SWEA_7733 {

    static int N;
    static int[][] board;
    static boolean[][] visited;

    static int[] nx = { 0, 1, 0, -1 };
    static int[] ny = { -1, 0, 1, 0 };

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            N = Integer.parseInt(bf.readLine());

            board = new int[N][N];
            visited = new boolean[N][N];

            answer = Integer.MIN_VALUE;

            int max = Integer.MIN_VALUE;

            for (int j = 0; j < N; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                for (int q = 0; q < N; q++) {
                    board[j][q] = Integer.parseInt(s_arr[q]);
                    max = Math.max(max, board[j][q]);
                }
            }

            for (int j = 0; j < max; j++) {
                eat(j);

                int cnt = 0;

                for (int q = 0 ; q < N ; q++) {
                    for (int k = 0 ; k < N ; k++) {
                        if (!visited[q][k]) {
                            bfs(k, q);
                            cnt++;
                        }
                    }
                }

                answer = Math.max(answer, cnt);

                visited = new boolean[N][N];
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
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
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y));

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();

                visited[p.y][p.x] = true;

                for (int j = 0; j < 4; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx]) {
                        Q.add(new Point(dx, dy));
                        visited[dy][dx] = true;
                    }
                }
            }
        }
    }


    private static void eat(int e) {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (board[i][j] <= e) {
                    visited[i][j] = true;
                }
            }
        }
    }
}
