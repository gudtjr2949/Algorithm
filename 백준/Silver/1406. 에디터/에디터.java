import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        Stack<Character> stack1 = new Stack();
        Stack<Character> stack2 = new Stack();

        for (int i = 0 ; i < s.length() ; i++) {
            stack1.add(s.charAt(i));
        }

        int M = Integer.parseInt(bf.readLine());

        // L : stack2.add(stack1.pop());
        // D : stack1.add(stack2.pop());
        // B : stack1.pop();
        // P $ : stack1.add($);
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            char operation = st.nextToken().charAt(0);

            switch (operation) {
                case 'L':
                    if (!stack1.isEmpty()) stack2.add(stack1.pop());
                    break;
                case 'D':
                    if (!stack2.isEmpty()) stack1.add(stack2.pop());
                    break;
                case 'B':
                    if (!stack1.isEmpty()) stack1.pop();
                    break;
                case 'P':
                    char c = st.nextToken().charAt(0);
                    stack1.add(c);
                    break;
            }
        }

        char[] list1 = new char[stack1.size()];
        char[] list2 = new char[stack2.size()];

        int size = stack1.size()-1;

        for (int i = size ; i >= 0 ; i--) {
            list1[i] = stack1.pop();
        }

        size = stack2.size();
        for (int i = 0 ; i < size ; i++) {
            list2[i] = stack2.pop();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < list1.length ; i++) {
            sb.append(list1[i]);
        }

        for (int i = 0 ; i < list2.length ; i++) {
            sb.append(list2[i]);
        }

        System.out.println(sb);
    }
}