package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1992 : 쿼드트리
public class BOJ_1992 {

    static int N;
    static int[][] board;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        board = new int[N][N];
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        divide(0, 0, N);

        System.out.println(sb);
    }

    private static void divide(int x, int y, int size) {

        if (isPossible(x, y ,size)) {
            sb.append(board[y][x]);
            return;
        }

        sb.append("(");
        // 1사분면
        divide(x, y, size / 2);
        // 2사분면
        divide(x + (size / 2), y, size / 2);
        // 3사분면
        divide(x, y + (size / 2), size / 2);
        // 4사분면
        divide(x + (size / 2), y + (size / 2), size / 2);
        sb.append(")");
    }

    private static boolean isPossible(int x, int y, int size) {

        int value = board[y][x];

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (value != board[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
}