import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] S, H;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = new int[N];
        H = new int[N];

        dp = new int[N+1][101];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][100] = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j <= 100 ; j++) {
                if (dp[i][j] == -1) continue;

                // 해당 구간을 클리어한 경우
                int nextH = j - H[i];
                if (nextH >= 0) {
                    nextH = Math.min(100, nextH + K);
                    dp[i+1][nextH] = Math.max(dp[i][j] + S[i], dp[i+1][nextH]);
                }

                // i 구간을 그냥 넘어간 경우
                nextH = Math.min(100, j + K);
                dp[i+1][nextH] = Math.max(dp[i][j], dp[i+1][nextH]);
            }
        }

        for (int i = 0 ; i <= N ; i++) {
            for (int j = 0 ; j <= 100 ; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
    }
}