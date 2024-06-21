import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, x;
    static int[] L, C, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        L = new int[N];
        C = new int[N];
        dp = new int[x+1];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            L[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(dp[x]);
    }

    static void solve() {
        dp[0] = 1;

        for (int i = 0 ; i < N ; i++) {
            int len = L[i];
            int cnt = C[i];

            for (int j = x ; j >= 0 ; j--) {
                for (int k = 1 ; k <= cnt ; k++) {
                    if (len * k > j) break;
                    dp[j] += dp[j - len * k];
                }
            }
        }
    }
}