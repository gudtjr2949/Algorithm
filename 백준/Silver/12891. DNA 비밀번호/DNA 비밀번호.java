import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int S, P, A, C, G, T, answer;
    static String s;
    static int[] alpha;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        s = bf.readLine();
        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        alpha = new int[26];

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < P ; i++) {
            alpha[s.charAt(i) - 'A']++;
        }

        if (check()) answer++;

        for (int i = 0 ; i < S-P ; i++) {
            // i 빼기
            alpha[s.charAt(i) - 'A']--;
            // i+P 넣기
            alpha[s.charAt(i+P) - 'A']++;

            if (check()) answer++;
        }
    }

    static boolean check() {
        return alpha['A'-'A'] >= A && alpha['C'-'A'] >= C && alpha['G'-'A'] >= G && alpha['T'-'A'] >= T;
    }

}