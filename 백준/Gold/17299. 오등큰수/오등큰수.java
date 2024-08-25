import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A, F, NGF;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        A = new int[N];
        NGF = new int[N];

        int max = 0;

        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, A[i]);
        }

        F = new int[max+1];
        for (int i = 0 ; i < N ; i++) {
            F[A[i]]++;
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; i++) {
            sb.append(NGF[i]).append(" ");
        }

        System.out.println(sb);
    }

    static void solve() {
        Stack<Integer> stack = new Stack<>();
        stack.add(A[N-1]);
        NGF[N-1] = -1;

        for (int i = N-2 ; i >= 0 ; i--) {
            while (!stack.isEmpty() && F[A[i]] >= F[stack.peek()]) {
                stack.pop();
            }

            NGF[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.add(A[i]);
        }
    }
}