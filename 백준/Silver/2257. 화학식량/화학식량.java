import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    static int answer;
    static String s;
    static Map<Character, Integer> map;
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        stack = new Stack<>();
        map = new HashMap<>();
        map.put('H', 1);
        map.put('C', 12);
        map.put('O', 16);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < s.length() ; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int tmp = stack.pop();
                tmp *= s.charAt(i) - '0';
                stack.add(tmp);
            } else if (s.charAt(i) == '(') {
                stack.push(-1);
            } else if (s.charAt(i) == ')') {
                int tmp = stack.pop();
                int sum = 0;
                while (tmp != -1) {
                    sum += tmp;
                    tmp = stack.pop();
                }
                stack.add(sum);
            } else {
                stack.add(map.get(s.charAt(i)));
            }
        }

        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
    }
}