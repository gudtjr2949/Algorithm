import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] alpha, input;
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve() {
        Arrays.sort(alpha);
        dfs(0, 0);
    }

    static void dfs(int idx, int cur) {
        if (idx == L) {
            check();
            return;
        }

        for (int i = cur ; i < C ; i++) {
            input[idx] = alpha[i];
            dfs(idx+1, i+1);
        }
    }

    static void check() {
        int vowelsCnt = 0;
        int consonantCnt = 0;

        for (int i = 0 ; i < L ; i++) {
            if (vowels.contains(input[i])) vowelsCnt++;
            else consonantCnt++;
        }

        if (vowelsCnt >= 1 && consonantCnt >= 2) print();
    }

    static void print() {
        for (int i = 0 ; i < L ; i++) System.out.print(input[i]);
        System.out.println();
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < C ; i++) alpha[i] = st.nextToken().charAt(0);
    }

    static void init() {
        alpha = new char[C];
        input = new char[L];
    }
}