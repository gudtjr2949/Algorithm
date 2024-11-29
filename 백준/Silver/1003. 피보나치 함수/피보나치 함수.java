import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        sb = new StringBuilder();
        dp = new int[41][2];

        for (int i = 0 ; i < 41 ; i++)
            Arrays.fill(dp[i], -1);

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        while (T-- > 0) {
            int N = Integer.parseInt(bf.readLine());
            fibonacci(N);
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }

        System.out.println(sb);
    }

    static int[] fibonacci(int N) {
        if (dp[N][0] == -1 || dp[N][1] == -1) {
            dp[N][0] = fibonacci(N-1)[0] + fibonacci(N-2)[0];
            dp[N][1] = fibonacci(N-1)[1] + fibonacci(N-2)[1];
        }

        return dp[N];
    }
}