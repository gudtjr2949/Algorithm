import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            String s = bf.readLine();
            Stack<Character> stack = new Stack<>();

            String result = "YES";

            for (int i = 0 ; i < s.length() ; i++) {
                if (s.charAt(i) == ')') {
                    if (!stack.isEmpty())
                        stack.pop();
                    else {
                        result = "NO";
                        break;
                    }
                } else {
                    stack.add('(');
                }
            }

            if (!stack.isEmpty()) result = "NO";
            
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}