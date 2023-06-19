package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 9465 : 스티커
public class BOJ_9465 {

    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        for (int Test = 0 ; Test < T ; Test++) {
            N = Integer.parseInt(bf.readLine());

            map = new int[2][N+1];
            dp = new int[2][N+1];

            int idx = 0;

            for (int i = 0 ; i < 2 ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());

                for (int j = 1 ; j <= N ; j++) {
                    map[idx][j] = Integer.parseInt(st.nextToken());
                }

                idx++;
            }

            dp[0][1] = map[0][1];
            dp[1][1] = map[1][1];

            solve();

            sb.append(findAns()).append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        for (int j = 2 ; j <= N ; j++) {
            for (int i = 0 ; i < 2 ; i++) {
                if (i == 0) {
                    dp[i][j] = Math.max(map[i][j] + dp[i+1][j-1],
                            Math.max(map[i][j] + dp[i][j-2], map[i][j] + dp[i+1][j-2]));
                }
                else {
                    dp[i][j] = Math.max(map[i][j] + dp[i-1][j-1],
                            Math.max(map[i][j] + dp[i][j-2], map[i][j] + dp[i-1][j-2]));
                }
            }
        }
    }

    private static int findAns() {
        int answer = 0;

        for (int i = 0 ; i < 2 ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer;
    }
}
