import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] answer;
    static Stack<Integer> stack1, stack2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = new int[N];
        stack1 = new Stack<>();
        stack2 = new Stack<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            stack1.add(Integer.parseInt(st.nextToken()));
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        int idx = N-1;

        while (!stack1.isEmpty()) {
            if (stack2.isEmpty()) {
                answer[idx--] = -1;
                stack2.add(stack1.pop());
                continue;
            }


            while (!stack1.isEmpty() && !stack2.isEmpty()) {
                if (stack1.peek() >= stack2.peek()) {
                    stack2.pop();
                } else {
                    answer[idx--] = stack2.peek();
                    stack2.add(stack1.pop());
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            sb.append(answer[i]).append(" ");
        }
    }
}