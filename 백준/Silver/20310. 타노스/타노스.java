import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, zero, one;
    static String s;
    static boolean[] removed;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (s.charAt(i) == '0') zero++;
            else one++;
        }

        // 1 빼기
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            if (cnt == one/2) break;

            if (s.charAt(i) == '1') {
                removed[i] = true;
                cnt++;
            }
        }

        // 0 빼기
        cnt = 0;
        for (int i = N-1 ; i >= 0 ; i--) {
            if (cnt == zero/2) break;

            if (s.charAt(i) == '0') {
                removed[i] = true;
                cnt++;
            }
        }

        for (int i = 0 ; i < N ; i++) {
            if (!removed[i]) {
                sb.append(s.charAt(i));
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        init();
    }

    static void init() {
        N = s.length();
        removed = new boolean[N];
    }
}