import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int answer;
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        answer = 0;

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Stack<Character> stack = new Stack<>();
        int tmp = 1;
        for (int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                tmp *= 2;
                stack.add('(');
            } else if (c == '[') {
                tmp *= 3;
                stack.add('[');
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    return;
                } else if (s.charAt(i-1) == '(') {
                    answer += tmp;
                }
                stack.pop();
                tmp /= 2;
            } else {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    return;
                } else if (s.charAt(i-1) == '[') {
                    answer += tmp;
                }
                stack.pop();
                tmp /= 3;
            }
        }

        if (!stack.isEmpty()) answer = 0;
    }
}