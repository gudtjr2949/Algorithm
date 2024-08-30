import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dp[a][b] = -1;
            dp[b][a] = 1;
        }

        solve();

        int s = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < s ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(dp[a][b]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (dp[i][j] == 0) {
                        if (dp[i][k] == -1 && dp[k][j] == -1) {
                            dp[i][j] = -1;
                            dp[j][i] = 1;
                        } else if (dp[i][k] == 1 && dp[k][j] == 1) {
                            dp[i][j] = 1;
                            dp[j][i] = -1;
                        }
                    }
                }
            }
        }


    }
}