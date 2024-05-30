import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        StringBuilder sb  = new StringBuilder();

        int idx = 1;

        while (check(s)) {
            sb.append(idx).append(". ").append(solve()).append("\n");

            s = bf.readLine();

            idx++;
        }

        System.out.println(sb);
    }

    static int solve() {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '}') {
                if (!stack.isEmpty())
                    stack.pop();
                else {
                    cnt++;
                    stack.push('{');
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        // Stack 에 남아있는 괄호들도 계산해야 함
        cnt += stack.size() / 2;

        return cnt;
    }

    static boolean check(String s) {
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '-') return false;
        }

        return true;
    }
}