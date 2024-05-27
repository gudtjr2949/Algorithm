import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M, answer;
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        solve();

        System.out.println(answer);
    }

    static void solve() {
        String str = "";
        for (int i = 0 ; i < 2 * N + 1 ; i++) {
            if (i % 2 == 0) {
                str += "I";
            } else {
                str += "O";
            }
        }

        for (int i = 0 ; i <= M - (2 * N + 1); i++) {
            if (s.charAt(i) == 'I') {
                String tmp = "";
                for (int j = i; j < i + (2 * N + 1); j++) {
                    tmp += s.charAt(j);
                }

                if (str.equals(tmp)) {
                    answer++;
                }
            }
        }
    }
}