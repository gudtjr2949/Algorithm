package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1025 : 제곱수 찾기
public class BOJ_1025 {

    static int N;
    static int M;
    static char[][] board;
    static int[] ny = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] nx = {0, 1, 1, 1, 0, -1, -1, -1};
    static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nm = bf.readLine();
        String[] nm_arr = nm.split(" ");

        N = Integer.parseInt(nm_arr[0]);
        M = Integer.parseInt(nm_arr[1]);

        board = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        solve();

        if (max == Long.MIN_VALUE)
            System.out.println(-1);
        else
            System.out.println(max);

    }

    private static void solve() {
        for (int i = 0 ; i < N ; i++) { // y
            for (int j = 0 ; j < M ; j++) { // x
                for (int q = 0 ; q < 8 ; q++) { // 방향벡터
                    for (int k = 1 ; k <= N ; k++) { // y 공차
                        for (int l = 1 ; l <= M ; l++) { // x 공차
                            int dx = j; // x 시작점
                            int dy = i; // y 시작점

                            String tmp = "";

                            while (!(dx >= M || dx < 0 || dy >= N || dy < 0)) { // N * M 범위 안에 있을 때
                                tmp += board[dy][dx];
                                find_max(tmp);
                                dx += nx[q] * l; // x 공차 더하기
                                dy += ny[q] * k; // y 공차 더하기
                            }
                        }
                    }
                }

            }
        }
    }

    private static void find_max(String s) {
        long num = 0;

        for (int i = 0 ; i < s.length() ; i++) {
            num *= 10;
            num += s.charAt(i) - '0';
        }

        Double sqrt = Math.sqrt(num);

        // 제곱근인지 확인
        if (sqrt == sqrt.intValue()) {
            max = Math.max(max, num);
        }
    }
}
