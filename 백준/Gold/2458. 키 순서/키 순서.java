import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i == j) continue;

                    if (dp[i][k] && dp[k][j]) {
                        dp[i][j] = true;
                    }

//                    else if (dp[i][k] == -1 && dp[k][j] == -1) {
//                        dp[i][j] = -1;
//                        dp[j][i] = 1;
//                    }

                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            boolean flag = true;
            for (int j = 1 ; j <= N ; j++) {
                if (i == j) continue;

                if (!dp[i][j] && !dp[j][i]) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());
            dp[small][big] = true;
        }
    }

    static void init() {
        dp = new boolean[N+1][N+1];
    }
}