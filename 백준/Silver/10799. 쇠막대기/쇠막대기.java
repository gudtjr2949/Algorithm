import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));

        int answer = 0;

        for (int i = 1 ; i < s.length() ; i++) {
            if (s.charAt(i-1) == '(' && s.charAt(i) == ')') { // 레이저 발사
                stack.pop();
                answer += stack.size();
            } else if (s.charAt(i) == '(') {
                stack.push('(');
            } else {
                answer++;
                stack.pop();
            }
        }

        System.out.println(answer);
    }
}