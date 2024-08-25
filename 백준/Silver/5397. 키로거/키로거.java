import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            s = bf.readLine();
            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '<':
                    if (!stack1.isEmpty()) stack2.add(stack1.pop());
                    break;
                case '>':
                    if (!stack2.isEmpty()) stack1.add(stack2.pop());
                    break;
                case '-':
                    if (!stack1.isEmpty()) stack1.pop();
                    break;
                default:
                    stack1.add(c);
                    break;
            }
        }

        char[] result1 = new char[stack1.size()];
        char[] result2 = new char[stack2.size()];

        int size = stack1.size();

        for (int i = size - 1; i >= 0; i--) {
            result1[i] = stack1.pop();
        }

        size = stack2.size();

        for (int i = 0; i < size ; i++) {
            result2[i] = stack2.pop();
        }

        for (int i = 0; i < result1.length; i++) {
            sb.append(result1[i]);
        }

        for (int i = 0; i < result2.length; i++) {
            sb.append(result2[i]);
        }

        sb.append("\n");
    }
}