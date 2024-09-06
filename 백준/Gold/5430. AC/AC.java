import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static String p;
    static StringBuilder sb;
    static Deque<String> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        sb = new StringBuilder();

        while (T-- > 0) {
            p = bf.readLine();
            N = Integer.parseInt(bf.readLine());
            String s1 = bf.readLine();
            String s2 = s1.replace("[", "");
            String s3 = s2.replace("]", "");
            StringTokenizer st = new StringTokenizer(s3, ",");

            sb.append(solve(st)).append("\n");
        }

        System.out.println(sb);
    }

    static String solve(StringTokenizer st) {
        for (int i = 0; i < N ; i++) {
            dq.add(st.nextToken());
        }

        boolean error = false;
        int first = 1;

        for (int i = 0 ; i < p.length() ; i++) {
            if (p.charAt(i) == 'R') {
                first *= -1;
            } else {
                if (dq.size() == 0) {
                    return "error";
                }

                if (first == 1)
                    dq.pollFirst();
                else
                    dq.pollLast();
            }
        }

        StringBuilder s = new StringBuilder();
        s.append("[");
        while (!dq.isEmpty()) {
            if (first == 1) {
                s.append(dq.pollFirst()).append(",");
            } else {
                s.append(dq.pollLast()).append(",");
            }
        }

        if (s.length() > 1)
            s.deleteCharAt(s.length()-1);

        s.append("]");

        return s.toString();
    }
}