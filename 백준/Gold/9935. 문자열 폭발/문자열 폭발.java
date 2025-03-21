import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s, bomb;
    static StringBuilder sb = new StringBuilder();
    static Stack<Character> stack;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        setAnswer();
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < s.length() ; i++) {
            stack.add(s.charAt(i));

            if (stack.size() >= bomb.length() && check()) {
                for (int j = 0 ; j < bomb.length() ; j++) {
                    stack.pop();
                }
            }
        }
    }

    static boolean check() {
        int size = stack.size() - bomb.length();

        for (int i = 0 ; i < bomb.length() ; i++) {
            if (bomb.charAt(i) != stack.get(size++)) {
                return false;
            }
        }

        return true;
    }

    static void setAnswer() {
        if (stack.isEmpty()) {
            sb.append("FRULA");
            return;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        sb = sb.reverse();
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        bomb = bf.readLine();
        init();
    }

    static void init() {
        stack = new Stack<>();
    }
}