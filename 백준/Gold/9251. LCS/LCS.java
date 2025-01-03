import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String s1, s2;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s1 = bf.readLine();
        s2 = bf.readLine();

        dp = new int[s1.length()+1][s2.length()+1];

        solve();

        System.out.println(dp[s1.length()][s2.length()]);
    }

    static void solve() {
        for (int i = 1 ; i <= s1.length() ; i++) {
            for (int j = 1 ; j <= s2.length() ; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }


}