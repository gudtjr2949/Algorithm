package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// 백준 2210 : 숫자판 점프
public class BOJ_2210 {
    static Set<String> set = new HashSet<>();
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[5][5];

        int[] output = new int[6];

        for (int i = 0; i < 5; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(s_arr[j]);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                solve(board, output, 0, 25, 6, i, j); // 25개의 숫자중에 6개 뽑기
            }
        }

        System.out.println(set.size());
    }

    private static void solve(int[][] board, int[] output, int depth, int n, int r, int x, int y) {
        if (depth == r) {
            String tmp = "";
            for (int i = 0; i < 6; i++)
                tmp += output[i];

            set.add(tmp);

            return;
        }

        output[depth] = board[y][x]; // 이동 이후 값 넣기

        for (int q = 0; q < 4; q++) {
            int dx = x + nx[q];
            int dy = y + ny[q];

            if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5) { // 이동 가능하다면
                output[depth] = board[y][x];

                solve(board, output, depth + 1, n, r, dx, dy);
            }
        }
    }
}
