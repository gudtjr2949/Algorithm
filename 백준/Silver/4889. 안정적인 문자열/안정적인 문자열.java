import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int round;
    static String s;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        while (!(s = bf.readLine()).contains("-")) {
            round++;
            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        Stack<Character> stack = new Stack<>();
        int changeCnt = 0;

        for (int i = 0 ; i < s.length() ; i++) {
            if (stack.isEmpty()) {
                stack.add(s.charAt(i));
            } else {
                if (stack.peek() == '{' && s.charAt(i) == '}') {
                    stack.pop();
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }

        int idx = 0;

        while (!stack.isEmpty()) {
            if (idx % 2 == 0) {
                if (stack.pop() != '}') {
                    changeCnt++;
                }
            } else {
                if (stack.pop() != '{') {
                    changeCnt++;
                }
            }

            idx++;
        }

        sb.append(round).append(". ").append(changeCnt).append("\n");
    }
}