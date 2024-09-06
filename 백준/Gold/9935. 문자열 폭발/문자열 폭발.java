import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s, boom;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        boom = bf.readLine();
        sb = new StringBuilder();

        solve();

        if (sb.length() == 0) System.out.println("FRULA");
        else System.out.println(sb.reverse());
    }

    static void solve() {
        Stack<Character> stack = new Stack<>();

        for (int i = 0 ; i < s.length() ; i++) {
            stack.add(s.charAt(i));

            if (stack.size() >= boom.length()) {
                boolean flag = true;
                for (int j = 0 ; j < boom.length() ; j++) {
                    if (stack.get(stack.size() - boom.length() + j) != boom.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0 ; j < boom.length() ; j++) stack.pop();
                }
            }
        }


        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }
}