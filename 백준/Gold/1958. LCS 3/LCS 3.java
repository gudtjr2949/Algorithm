import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String str1, str2, str3;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str1 = bf.readLine();
        str2 = bf.readLine();
        str3 = bf.readLine();

        dp = new int[101][101][101];

        solve();
        System.out.println(dp[str1.length()][str2.length()][str3.length()]);
    }

    static void solve() {
        for (int i = 1 ; i <= str1.length() ; i++) {
            for (int j = 1 ; j <= str2.length() ; j++) {
                for (int k = 1 ; k <= str3.length() ; k++) {
                    if (str1.charAt(i-1) == str2.charAt(j-1) && str2.charAt(j-1) == str3.charAt(k-1)) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }
        }
    }
}