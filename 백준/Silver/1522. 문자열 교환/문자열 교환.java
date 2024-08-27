import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String s;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        answer = Integer.MAX_VALUE;
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int bCnt = 0;

        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'b') bCnt++;
        }

        if (bCnt == 0 || bCnt == s.length()) {
            answer = 0;
            return;
        }

        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'b') {
                int tmp = 0;
                for (int j = i ; j < i + bCnt ; j++) {
                    if (j >= s.length()) {
                        if (s.charAt(j-s.length()) == 'b') tmp++;
                    } else {
                        if (s.charAt(j) == 'b') tmp++;
                    }
                }

                answer = Math.min(answer, bCnt - tmp);
            }
        }
    }
}