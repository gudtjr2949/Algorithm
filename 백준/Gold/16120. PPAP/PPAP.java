import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String s;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        input();
        if (solve()) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }

    static boolean solve() {
        for (int i = 0 ; i < s.length() ; i++) {
            stack.add(s.charAt(i));

            if (check()) {
                for (int j = 0 ; j < 4 ; j++) stack.pop();
                stack.add('P');
            }
        }

        if (stack.size() == 1 && stack.peek() == 'P') {
            return true;
        }

        return false;
    }

    static boolean check() {
        if (stack.size() < 4) return false;

        int idx = stack.size()-1;

        if (stack.get(idx) == 'P'
                && stack.get(idx-1) == 'A'
                && stack.get(idx-2) == 'P'
                && stack.get(idx-3) == 'P') {
            return true;
        }

        return false;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
    }
}