import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static String[] arr;
    static boolean[] alpha;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        knowAntatica();

        if (K >= 5) {
            dfs(0, 0);
        }
    }

    static void dfs(int cur, int cnt) {
        if (cnt >= K-5) {
            check();
            return;
        }

        for (int i = cur ; i < 26 ; i++) {
            if (!alpha[i]) {
                alpha[i] = true;
                dfs(i+1, cnt+1);
                alpha[i] = false;
            }
        }
    }

    static void check() {
        int know = 0;
        for (String s : arr) {
            boolean flag = true;
            for (int i = 0 ; i < s.length() ; i++) {
                if (!alpha[s.charAt(i) - 'a']) {
                    flag = false;
                    break;
                }
            }

            if (flag) know++;
        }

        answer = Math.max(answer, know);
    }

    static void knowAntatica() {
        alpha['a' - 'a'] = true;
        alpha['c' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['n' - 'a'] = true;
        alpha['t' - 'a'] = true;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = bf.readLine();
        }
    }

    static void init() {
        arr = new String[N];
        alpha = new boolean[26];
        // a, c, i, n, t는 알아야 함
    }
}