import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int K;
    static String W;
    static int[] answer, alpha;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            W = bf.readLine();
            K = Integer.parseInt(bf.readLine());
            answer = new int[2];
            answer[0] = Integer.MAX_VALUE;
            answer[1] = -1;

            if (K == 1) {
                answer[0] = 1;
                answer[1] = 1;
            } else {
                solve();
            }

            if (answer[0] == Integer.MAX_VALUE || answer[1] == -1) sb.append(-1).append("\n");
            else sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        alpha = new int[26];
        for (int i = 0 ; i < W.length() ; i++) {
            alpha[W.charAt(i)-'a']++;
        }

        for (int i = 0 ; i < W.length() ; i++) {
            if (alpha[W.charAt(i)-'a'] < K) continue;

            int cnt = 1;

            for (int j = i+1 ; j < W.length() ; j++) {
                if (W.charAt(i) == W.charAt(j)) cnt++;
                if (cnt == K) {
                    answer[0] = Math.min(answer[0], j - i + 1);
                    answer[1] = Math.max(answer[1], j - i + 1);
                    break;
                }
            }
        }
    }
}