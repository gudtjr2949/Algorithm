import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new int[123][123];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String a = st.nextToken();
            st.nextToken();
            String b = st.nextToken();

            if (a.equals(b)) continue;

            dp[a.charAt(0)][b.charAt(0)] = 1;
        }

        solve();

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 123 ; i++) {
            for (int j = 0 ; j < 123 ; j++) {
                if (dp[i][j] == 1) {
                    cnt++;
                    sb.append((char) i).append(" => ").append((char) j).append("\n");
                }
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    static void solve() {
        for (int k = 0 ; k < 123 ; k++) {
            for (int i = 0 ; i < 123 ; i++) {
                for (int j = 0 ; j < 123 ; j++) {
                    if (i == j || dp[i][j] != 0) continue;

                    if (dp[i][k] == 1 && dp[k][j] == 1) {
                        dp[i][j] = 1;
                    }
                }
            }
        }
    }
}