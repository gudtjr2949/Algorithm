package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 1954 : 달팽이 숫자
public class SWEA_1954 {

    static int[][] input;
    static int cnt;
    static int[] nx = {1, 0, -1, 0};
    static int[] ny = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            int N = Integer.parseInt(bf.readLine());
            cnt = 0;

            input = new int[N][N];

            int start = 0;

            int end = N;

            for (int j = 0 ; j < N/2 ; j++) {
                solve(start, end, 0);

                start++;
                end--;
            }

            if (N % 2 != 0) { // 가운데 채우기
                input[N/2][N/2] = cnt+1;
            }

            System.out.println("#" + (i+1));
            print();

        }
    }

    /*
    start : 시작 x, y좌표
    end : x, y 끝 좌표
    dir : 회전 방향
     */

    private static void solve(int start, int end, int dir) {

        int dx = start;
        int dy = start;

        while (dir < 4) {
            if (dx + nx[dir] >= end || dx + nx[dir] < start || dy + ny[dir] >= end || dy + ny[dir] < start) {
                dir++;
            }
            else {
                cnt++;
                input[dy][dx] = cnt;
                dx += nx[dir];
                dy += ny[dir];
            }
        }
    }

    private static void print() {
        for (int i = 0 ; i < input.length ; i++) {
            for (int j = 0 ; j < input[0].length ; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}
