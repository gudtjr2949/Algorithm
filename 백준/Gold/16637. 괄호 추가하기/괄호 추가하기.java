import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer = Integer.MIN_VALUE;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new char[N];
        String s = bf.readLine();

        for (int i = 0 ; i < N ; i++)
            arr[i] = s.charAt(i);

        dfs(2, arr[0] - '0');

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        if (idx >= N) {
            answer = Math.max(answer, sum);
            return;
        }

        // 괄호 X
        dfs(idx+2, cal(sum, arr[idx] - '0', arr[idx-1]));

        // 괄호 O
        if (idx+2 < N) {
            int tmp = cal(arr[idx] - '0', arr[idx+2] - '0', arr[idx+1]);
            dfs(idx+4, cal(sum, tmp, arr[idx-1]));
        }
    }

    static int cal(int num1, int num2, char operation) {
        if (operation == '*')
            return num1 * num2;
        else if (operation == '+')
            return num1 + num2;
        else
            return num1 - num2;
    }
}