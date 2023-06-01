package coding_test.백준;

import java.io.*;

// 백준 1012 : 유기농 배추
public class BOJ_1012 {
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String nmk = bf.readLine();
            String[] nmk_arr = nmk.split(" ");

            int M = Integer.parseInt(nmk_arr[0]);
            int N = Integer.parseInt(nmk_arr[1]);
            int K = Integer.parseInt(nmk_arr[2]);

            int[][] board = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");

                int x = Integer.parseInt(s_arr[0]);
                int y = Integer.parseInt(s_arr[1]);

                board[y][x] = 1;
            }

            int cnt = 0;

            for (int j = 0; j < N; j++) {
                for (int q = 0; q < M; q++) {
                    if (board[j][q] == 1 && !visited[j][q]) { // 배추가 있는데 해당 위치에 온 적이 없는 경우 -> 전수조사
                        dfs(board, visited, N, M, q, j);
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }


    private static void dfs(int[][] board, boolean[][] visited, int N, int M, int x, int y) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) { // 사방탐색
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && board[dy][dx] == 1 && !visited[dy][dx]) {
                dfs(board, visited, N, M, dx, dy);
            }
        }
    }
}
