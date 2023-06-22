package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1018 : 체스판 다시 칠하기
public class BOJ_1018 {

    static int N, M, answer;
    static char[][] map;
    static char[][][] chase;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        chase = new char[2][8][8];

        answer = Integer.MAX_VALUE;

        make_chase();

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();

            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        solve();

        System.out.println(answer);
    }

    private static void solve() {
        for (int i = 0 ; i <= N-8 ; i++) {
            for (int j = 0 ; j <= M-8 ; j++) {
                for (int k = 0 ; k < 2 ; k++) {

                    int cnt = 0;

                    for (int y = 0 ; y < 8 ; y++) {
                        for (int x = 0 ; x < 8 ; x++) {
                            if (chase[k][y][x] != map[i+y][j+x]) {
                                cnt++;
                            }
                        }
                    }

                    answer = Math.min(answer, cnt);
                }
            }
        }
    }

    private static void make_chase() {
        chase[0] = new char[][]{
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
        };

        chase[1] = new char[][]{
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
                {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
        };
    }
}
