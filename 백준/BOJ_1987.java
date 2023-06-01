package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1987 : 알파벳
public class BOJ_1987 {

    static int R;
    static int C;
    static char[][] board;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static int answer;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String rc = bf.readLine();
        String[] rc_arr = rc.split(" ");

        R = Integer.parseInt(rc_arr[0]);
        C = Integer.parseInt(rc_arr[1]);
        board = new char[R][C];
        answer = Integer.MIN_VALUE;

        for (int i = 0; i < R; i++) {
            String s = bf.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        solve(0, 0, 0, new char[R*C], new boolean[26]);

        System.out.println(answer);
    }

    private static void solve(int x, int y, int idx, char[] input, boolean[] visited) {
        if (idx == input.length) {
            return;
        }

        visited[board[y][x] - 'A'] = true;

        answer = Math.max(answer, idx + 1);

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (!(dx >= C || dx < 0 || dy >= R || dy < 0) && check(visited, board[dy][dx])) {
                visited[board[dy][dx] - 'A'] = true;
                input[idx] = board[dy][dx];
                solve(dx, dy, idx + 1, input, visited);
                visited[board[dy][dx] - 'A'] = false;
            }
        }
    }

    private static boolean check(boolean[] visited, char c) {
        if (visited[c - 'A']) {
            return false;
        }

        return true;
    }
}
