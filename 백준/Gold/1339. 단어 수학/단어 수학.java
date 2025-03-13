import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long answer;
    static Integer[] alpha;
    static String[] words;


    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        // 각 자리수 체크
        for (String s : words) {
            for (int i = 0 ; i < s.length() ; i++) {
                Integer num = (int) Math.pow(10, (s.length()-1) - i);
                alpha[s.charAt(i) - 'A'] += num;
            }
        }

        Arrays.sort(alpha, Collections.reverseOrder());

        int idx = 9;
        for (int i = 0 ; i < 26 ; i++) {
            if (alpha[i] == 0) break;
            answer += alpha[i] * idx--;
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            words[i] = bf.readLine();
        }
    }

    static void init() {
        words = new String[N];
        alpha = new Integer[26];
        Arrays.fill(alpha, 0);
    }
}