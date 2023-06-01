package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2573 : 빙산
public class BOJ_2573 {

    static int N, M, answer, year;
    static int[][] map;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static boolean possible;

    /*
    1. 덩어리 몇 개인지 카운트 하고,
    2. 주변에 0인 곳이 몇 개 있는지 카운트 하고,
    3. 그 수 만큼 map에 있는 숫자 빼기
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = 0;
        year = 0;

        map = new int[N][M];

        possible = false;

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!possible) {
            solve();
        }

        System.out.println(answer);
    }

    private static void solve() {
        // 1. 덩어리 체크
        int m_cnt = 0;

        boolean[][] visited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    visited[i][j] = true;
                    mountain_count(j, i, visited);
                    m_cnt++;
                }
            }
        }

        if (m_cnt >= 2) {
            answer = year;
            possible = true;
            return;
        }

        if (m_cnt == 0) {
            possible = true;
            return;
        }

        int[][] zero_cnt = new int[N][M];

        // 2. 주변에 0인 곳 갯수 체크
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) {
                    zero_cnt[i][j] = zero_count(j, i);
                }
            }
        }

        // 3. 그 수 만큼 map에 있는 숫자 빼기
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) {
                    if (map[i][j] - zero_cnt[i][j] < 0) {
                        map[i][j] = 0;
                    }
                    else {
                        map[i][j] -= zero_cnt[i][j];
                    }
                }
            }
        }

        year++;
    }

    private static void mountain_count(int x, int y, boolean[][] visited) {
        for (int i = 0 ; i < 4 ; i++) {
            int dx = nx[i] + x;
            int dy = ny[i] + y;

            if (!visited[dy][dx] && map[dy][dx] != 0) {
                visited[dy][dx] = true;
                mountain_count(dx, dy, visited);
            }
        }
    }

    private static int zero_count(int x, int y) {

        int cnt = 0;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = nx[i] + x;
            int dy = ny[i] + y;

            if (map[dy][dx] == 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
