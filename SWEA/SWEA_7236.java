package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 7236 : 저수지의 물의 총 깊이 구하기
public class SWEA_7236 {

    static int[] nx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] ny = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N;
    static char[][] board;
    static int[][] cnt_board;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test; i++) {
            N = Integer.parseInt(bf.readLine());
            board = new char[N][N];
            cnt_board = new int[N][N];

            for (int j = 0 ; j < N ; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");

                for (int q = 0 ; q < N ; q++) {
                    board[j][q] = s_arr[q].charAt(0);
                }
            }

            solve();

            sb.append("#").append(i+1).append(" ").append(find_max()).append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {

                if (board[i][j] == 'W') {

                    for (int q = 0 ; q < 8 ; q++) {
                        int dx = j + nx[q];
                        int dy = i + ny[q];

                        if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && board[dy][dx] == 'W') {
                            cnt_board[i][j]++;
                        }
                    }
                }
            }
        }
    }

    private static int find_max() {
        int max = Integer.MIN_VALUE;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                max = Math.max(max, cnt_board[i][j]);
            }
        }

        if (max == 0)
            return 1;

        return max;
    }
}
