import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] hp, happy;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        hp = new int[N+1];
        happy = new int[N+1];
        dp = new int[N+1][100];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++)
            hp[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++)
            happy[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }

    static int solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j < 100 ; j++) {
                if (hp[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-hp[i]] + happy[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[N][99];
    }

}