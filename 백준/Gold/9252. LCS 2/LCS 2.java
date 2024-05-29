import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    static String str1, str2;
    static char[] answer;
    static int idx = 0;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        str1 = bf.readLine();
        str2 = bf.readLine();

        dp = new int[1001][1001];

        solve();
        System.out.println(dp[str2.length()][str1.length()]);
        answer = new char[dp[str2.length()][str1.length()]];
        idx = dp[str2.length()][str1.length()]-1;
        print(str2.length(), str1.length());
    }

    static void solve() {
        for (int i = 1 ; i <= str2.length() ; i++) {
            for (int j = 1 ; j <= str1.length() ; j++) {
                if (str2.charAt(i-1) == str1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }

    static void print(int i, int j) {
        Stack<Character> st = new Stack<>();
        while(i > 0 && j > 0) {
            if(i == 0 || j ==0) break;

            if(dp[i][j] == dp[i-1][j]) {
                i--;
            }else if(dp[i][j] == dp[i][j-1]) {
                j--;
            } else {
                st.push(str2.charAt(i-1));
                i--;
                j--;
            }
        }

        while(!st.isEmpty()) {
            System.out.print(st.pop());
        }
    }
}