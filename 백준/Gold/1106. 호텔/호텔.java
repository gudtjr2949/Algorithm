import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int C, N;
    static int[][] dp, promotions;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][C]);
    }

    static void solve() {
        Arrays.sort(promotions, (o1, o2) -> o1[0] - o2[0]);
        Arrays.fill(dp[0], 100_000);

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= C ; j++) {
                if (j-promotions[i-1][1] >= 0) dp[i][j] = Math.min(dp[i-1][j], promotions[i-1][0] + dp[i][j-promotions[i-1][1]]);
                else dp[i][j] = Math.min(dp[i-1][j], promotions[i-1][0]);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            promotions[i][0] = cost;
            promotions[i][1] = people;
        }
    }

    static void init() {
        dp = new int[N+1][C+1];
        promotions = new int[N][2];
    }
}