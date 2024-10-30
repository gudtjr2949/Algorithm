import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    static int N;
    static String P;
    static StringBuilder sb;
    static Deque<String> deque;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        sb = new StringBuilder();

        while (T-- > 0) {
            P = bf.readLine();
            N = Integer.parseInt(bf.readLine());
            deque = new ArrayDeque<>();
            String s = bf.readLine();
            s = s.substring(1, s.length()-1);
            String[] tmp = s.split(",");

            for (int i = 0 ; i < N ; i++) {
                deque.add(tmp[i]);
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        boolean dir = true; // TRUE : 앞에서, FALSE : 뒤에서
        for (int i = 0 ; i < P.length() ; i++) {
            if (P.charAt(i) == 'R') {
                dir = !dir;
            } else {
                if (deque.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }

                if (dir) deque.pollFirst();
                else deque.pollLast();
            }
        }

        sb.append("[");
        while (!deque.isEmpty()) {
            if (dir) {
                sb.append(deque.pollFirst());
            } else {
                sb.append(deque.pollLast());
            }

            if (deque.size() != 0)
                sb.append(",");
        }

        sb.append("]").append("\n");
    }
}