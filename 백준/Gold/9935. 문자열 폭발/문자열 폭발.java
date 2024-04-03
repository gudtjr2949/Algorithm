import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String bomb = bf.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0 ; i < s.length() ; i++) {
            stack.push(s.charAt(i));

            if (stack.size() >= bomb.length()) {
                boolean same = true;

                for (int j = 0 ; j < bomb.length() ; j++) {
                    if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
                        same = false;
                        break;
                    }
                }

                if (same) {
                    for (int j = 0 ; j < bomb.length() ; j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.size() == 0) {
            System.out.println("FRULA");
        } else {
            int size = stack.size();
            for (int i = 0 ; i < size ; i++) {
                sb.append(stack.pop());
            }

            System.out.println(sb.reverse().toString());
        }
    }
}