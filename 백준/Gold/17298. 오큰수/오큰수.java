import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        int[] nge = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = N-1 ; i >= 0 ; i--) {
            int num = arr[i];

            if (stack.isEmpty()) {
                nge[i] = -1;
                stack.push(num);
                continue;
            }

            if (num < stack.peek()) {
                nge[i] = stack.peek();
            } else {
                while (!stack.isEmpty() && stack.peek() <= num) {
                    // 그냥 pop() 해버리는 이유는 stack.peek() 은 어차피 num 보다 작은 수 이기 때문에 다음 arr[i] 에서도 고려하지 않아도 되는 수임
                    stack.pop();
                }

                nge[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(num); // num 이 arr[i] 의 오큰수가 될 수도 있기 때문에 넣어줘야 함
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; i++) {
            sb.append(nge[i]).append(" ");
        }

        System.out.println(sb);

    }
}