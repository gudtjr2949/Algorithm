package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 7699 : 수지의 수지 맞는 여행
public class SWEA_7699 {

    static char[][] board;

    static int answer;

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    static int R;
    static int C;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            String rc = bf.readLine();
            String[] rc_arr = rc.split(" ");

            R = Integer.parseInt(rc_arr[0]);
            C = Integer.parseInt(rc_arr[1]);

            board = new char[R][C];

            for (int j = 0 ; j < R ; j++) {
                String s = bf.readLine();
                for (int q = 0 ; q < C ; q++) {
                    board[j][q] = s.charAt(q);
                }
            }

            boolean sign = false;

            answer = Integer.MIN_VALUE;

            recursive(new boolean[26],0, 0, sign);

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    /*
    alpha : 들린 알파벳 배열
    x : board 에서 x 좌표
    y : board 에서 y 좌표
    sign : 이동 가능한 경로가 있는지 확인하는 변수
     */

    private static void recursive(boolean[] alpha, int x, int y, boolean sign) {
        if (sign) {
            int cnt = 0;

            for (int i = 0 ; i < alpha.length ; i++) {
                if (alpha[i])
                    cnt++;
            }

            answer = Math.max(answer, cnt);
            return;
        }


        alpha[board[y][x] - 'A'] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < C && dy >= 0 && dy < R) {
                if (!alpha[board[dy][dx] - 'A']) {
                    alpha[board[dy][dx] - 'A'] = true;
                    recursive(alpha, dx, dy ,sign);
                    alpha[board[dy][dx] - 'A'] = false;
                }
            }
        }

        sign = true; // 이동 가능한 경로 없음
        recursive(alpha, x, y ,sign);
    }
}
