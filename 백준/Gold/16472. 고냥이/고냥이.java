import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N, answer;
    static String s;
    static int[] alpha;
    static Set<Character> set;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }


    static void solve() {
        int left = 0;
        int right = 0;

        alpha[s.charAt(0) - 'a']++;
        set.add(s.charAt(0));

        while (left <= right && right < s.length()) {
            if (set.size() <= N) {
                right++;
                if (right >= s.length()) continue;

                alpha[s.charAt(right) - 'a']++;
                if (alpha[s.charAt(right) - 'a'] == 1) set.add(s.charAt(right));
            }

            if (set.size() > N) {
                alpha[s.charAt(left) - 'a']--;
                if (alpha[s.charAt(left) - 'a'] == 0) set.remove(s.charAt(left));

                left++;
            }

            if (set.size() <= N) answer = Math.max(answer, right - left + 1);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        init();
    }

    static void init() {
        alpha = new int[26];
        set = new HashSet<>();
    }
}