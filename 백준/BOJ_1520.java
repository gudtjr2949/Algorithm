package coding_test.백준;

import java.io.*;

// 백준 1520 : 내리막 길
public class BOJ_1520 {
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nm = bf.readLine();
        String[] nm_arr = nm.split(" ");

        int N = Integer.parseInt(nm_arr[1]);
        int M = Integer.parseInt(nm_arr[0]);

        int[][] board = new int[M][N];
        int[][] dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(s_arr[j]);
                dp[i][j] = -1; // dp[i][j]가 -1이란 것은 아직 방문하지 않았다는 말
            }
        }

        // 무조건 가장 최근 노드보다 작은 값으로 이동해야함
        System.out.println(dfs(board, dp, 0, 0, N, M));
    }

    private static int dfs(int[][] board, int[][] dp, int x, int y, int N, int M) {
        if (x == N-1 && y == M-1) { // 끝까지 간 경우
            return 1;
        }


        if (dp[y][x] != -1) // 해당 위치에 방문한 적이 있는 경우
            return dp[y][x];
        else { // 해당 위치에 처음 온 경우
            dp[y][x] = 0;

            for (int i = 0; i < 4; i++) {
                int dx = x + nx[i]; // 다음 x위치
                int dy = y + ny[i]; // 다음 y위치
                if (dy >= 0 && dy < M && dx >= 0 && dx < N) { // 다음 위치가 이동 가능한 경로인 지 확인
                    if (board[dy][dx] < board[y][x]) { // 다음 위치가 현재 위치보다 작은 수인지 확인
                        dp[y][x] += dfs(board, dp, dx, dy, N, M);
                    }
                }
            }
        }

        return dp[y][x];
    }
}