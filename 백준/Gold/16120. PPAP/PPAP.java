import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static boolean possible;
    static String s;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        possible = true;
        solve();

        if(stack.size() == 1 && possible) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }

    static void solve() {
        for(int i=0; i< s.length(); i++) {
            if(s.charAt(i) == 'P') {
                stack.push('P');
            } else {
                if(stack.size() >= 2 && i < s.length() -1 && s.charAt(i+1) == 'P') {
                    stack.pop();
                    stack.pop();
                } else {
                    possible = false;
                    return;
                }
            }
        }
    }
}