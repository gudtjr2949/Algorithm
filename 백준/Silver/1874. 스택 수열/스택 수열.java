import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int start = 0;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {

            int num = Integer.parseInt(bf.readLine());

            if (num > start) {
                // start + 1부터 입력받은 num 까지 push
                for (int j = start + 1; j <= num; j++) {
                    stack.push(j);
                    sb.append('+').append('\n');
                }
                start = num;    // 다음 push 할 때의 오름차순을 유지하기 위한 변수 초기화
            }

            if (stack.peek() != num) { // top 에 있는 원소가 입력받은 값과 같지 않은 경우
                System.out.println("NO");
                return;
            }
            
            stack.pop();
            sb.append('-').append('\n');

        }

        System.out.println(sb);
    }
}