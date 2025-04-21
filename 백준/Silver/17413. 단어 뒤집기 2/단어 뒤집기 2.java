import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '<') {
                while (s.charAt(i) != '>') {
                    sb.append(s.charAt(i++));
                }
                sb.append(s.charAt(i++));
            } else if (s.charAt(i) == ' ') {
                sb.append(" ");
                i++;
            } else {
                Stack<Character> stack = new Stack<>();
                while (i < s.length() && s.charAt(i) != '<') {
                    if (s.charAt(i) == ' ') {
                        break;
                    }
                    stack.add(s.charAt(i++));
                }

                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
    }
}