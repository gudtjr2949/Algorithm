package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 14712 : 넴모넴모 (Easy)
public class BOJ_14712 {

    static int N;
    static int M;

    static int[] nx = {0, 0, 1, 1};
    static int[] ny = {0, 1, 1, 0};

    static int[][] board;

    static int answer;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        N = Integer.parseInt(s_arr[0]);

        M = Integer.parseInt(s_arr[1]);

        board = new int[N][M];

        recursive(new int[N * M], 0);

        System.out.println(answer);

    }

    private static void recursive(int[] tmp_arr, int cur) {
        if (cur == tmp_arr.length) {

            int idx = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = tmp_arr[idx];
                    idx++;
                }
            }

            if (!check())
                answer++;

            return;
        }

        tmp_arr[cur] = 0;
        recursive(tmp_arr, cur + 1);
        tmp_arr[cur] = 1;
        recursive(tmp_arr, cur + 1);
    }


    private static boolean check() { // 만약에 "넴모" 가 있으면? return true, "넴모"가 없으면? return false;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                int cnt = 0;

                for (int q = 0; q < 4; q++) {
                    int dx = j + nx[q];
                    int dy = i + ny[q];

                    if (board[dy][dx] == 1)
                        cnt++;
                }

                if (cnt == 4)
                    return true;
            }
        }

        return false;
    }

}
