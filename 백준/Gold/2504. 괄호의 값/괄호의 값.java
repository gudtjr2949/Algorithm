import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int answer;
    static String s;
    static Stack<Character> stack;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int tmp = 1;

        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '(') {
                tmp *= 2;
                stack.add('(');
            } else if (s.charAt(i) == '[') {
                tmp *= 3;
                stack.add('[');
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }

                if (i-1 >= 0 && s.charAt(i-1) == '(') answer += tmp;

                tmp /= 2;
                stack.pop();
            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }
                
                if (i-1 >= 0 && s.charAt(i-1) == '[') answer += tmp;

                tmp /= 3;
                stack.pop();
            }
        }


        if (!stack.isEmpty()) answer = 0;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        init();
    }

    static void init() {
        stack = new Stack<>();
    }
}