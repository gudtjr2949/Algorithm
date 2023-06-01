package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 10163 : 색종이
public class BOJ_10163 {

    static int N;
    static Point[] p;
    static int[][] board;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        p = new Point[N];
        board = new int[1001][1001];
        cnt = new int[N];

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            p[i] = new Point(Integer.parseInt(s_arr[0]), Integer.parseInt(s_arr[1]), Integer.parseInt(s_arr[2]), Integer.parseInt(s_arr[3]));
        }

        solve();

        count();

        for (int i = 0 ; i < N ; i++)
            System.out.println(cnt[i]);
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            int x = p[i].x;
            int y = p[i].y;
            int width = p[i].width;
            int height = p[i].height;

            for (int j = y; j < y + height; j++) {
                for (int q = x; q < x + width; q++) {
                    board[j][q] = i + 1;
                }
            }
        }
    }

    private static void count() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 1001; j++) {
                for (int q = 0; q < 1001; q++) {
                    if (board[j][q] == i+1) {
                        cnt[i]++;
                    }
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        int width;
        int height;

        public Point(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
}
