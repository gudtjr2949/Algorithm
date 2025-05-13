import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static String s, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            s = bf.readLine();
            solve();
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Stack<Character> stack = new Stack<>();
        boolean flag = true;

        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '(') {
                stack.add('(');
            } else {
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) flag = false;

        if (flag) answer = "YES";
        else answer = "NO";
    }
}