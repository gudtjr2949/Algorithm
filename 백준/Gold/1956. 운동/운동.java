import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        dp = new int[V+1][V+1];
        for (int i = 0 ; i <= V ; i++) {
            for (int j = 0 ; j <= V ; j++) {
                if (i != j) dp[i][j] = 100_000_000;
            }
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dp[a][b] = c;
        }

        int answer = solve();

        System.out.println(answer == 100_000_000 ? -1 : answer);
    }

    static int solve() {
        for (int k = 1 ; k <= V ; k++) {
            for (int i = 1 ; i <= V ; i++) {
                for (int j = 1 ; j <= V ; j++) {
                    if (i != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        int answer = 100_000_000;

        for (int i = 1 ; i <= V ; i++) {
            for (int j = 1 ; j <= V ; j++) {
                if (i != j && (dp[i][j] != Integer.MAX_VALUE && dp[j][i] != Integer.MAX_VALUE)) {
                    answer = Math.min(answer, dp[i][j] + dp[j][i]);
                }
            }
        }

        return answer;
    }
}