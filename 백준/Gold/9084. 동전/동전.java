import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            coin = new int[N];
            for (int i = 0 ; i < N ; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(bf.readLine());

            dp = new int[M+1];
            dp[0] = 1;

            solve();

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = coin[i] ; j <= M ; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
    }
}