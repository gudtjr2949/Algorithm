import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, MAX = 100_001;
    static int[][] dp;
    static int[] coffee;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        coffee = new int[N+1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            coffee[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1 ; i <= K ; i++)
            dp[0][i] = MAX;

        solve();

        int answer = 0;
        
        if (K == 0) answer = 0;
        else if (dp[N][K] == MAX) answer = -1;
        else answer = dp[N][K];

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= K ; j++) {
                if (coffee[i] > j) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-coffee[i]]+1);
                }
            }
        }
    }
}