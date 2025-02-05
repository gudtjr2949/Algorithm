import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, answer;
    static Stack<Node> stack;

    static class Node {
        int num, idx;

        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        printAnswer();
    }

    static void printAnswer() {
        for (int i = 0 ; i < N ; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static void solve(StringTokenizer st) {
        for (int i = 0 ; i < N ; i++) {
            int now = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek().num < now) {
                stack.pop();
            }

            if (!stack.isEmpty()) answer[i] = stack.peek().idx;

            stack.add(new Node(now, i+1));
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        solve(st);
    }

    static void init() {
        arr = new int[N];
        answer = new int[N];
        stack = new Stack<>();
    }
}