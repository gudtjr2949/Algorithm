package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


// SWEA 1868 : 파핑파핑 지뢰찾기
public class SWEA_1868 {

    static int[] nx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] ny = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N;
    static char[][] board;
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            N = Integer.parseInt(bf.readLine());
            board = new char[N][N];
            visited = new boolean[N][N];
            cnt = 0;

            for (int j = 0; j < N; j++) {
                String s = bf.readLine();

                for (int q = 0; q < N; q++) {
                    board[j][q] = s.charAt(q);
                }
            }

            find_cnt();

            for (int j = 0 ; j < N ; j++) {
                for (int q = 0 ; q < N ; q++) {
                    if (board[j][q] == '0' && !visited[j][q]) {
                        bfs(q, j);
                        cnt++;
                    }
                }
            }

            for (int j = 0 ; j < N ; j++) {
                for (int q = 0 ; q < N ; q++) {
                    if (board[j][q] != '*' && !visited[j][q]) {
                        cnt++;
                    }
                }
            }

            sb.append("#").append(i+1).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void find_cnt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {

                    int mine_cnt = 0;

                    for (int q = 0 ; q < 8 ; q++) {
                        int dx = j + nx[q];
                        int dy = i + ny[q];

                        if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && board[dy][dx] == '*') {
                            mine_cnt++;
                        }
                    }
                    board[i][j] = (char) (mine_cnt + '0');
                }
            }
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

                for (int j = 0 ; j < 8 ; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && board[dy][dx] != '*' && !visited[dy][dx]) {
                        visited[dy][dx] = true;
                        if (board[dy][dx] == '0') {
                            Q.add(new Point(dx, dy));
                        }
                    }

                }
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
