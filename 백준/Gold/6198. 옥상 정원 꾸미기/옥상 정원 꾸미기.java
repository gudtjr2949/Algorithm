import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(bf.readLine());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);

        for (int i = 1 ; i < N ; i++) {
            int height = arr[i];

            while (!stack.empty() && stack.peek() <= height) {
                stack.pop();
            }

            answer += stack.size();

            stack.add(height);
        }
    }
}