import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, min, max;
    static int[] arr, operators;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(max);
        System.out.println(min);
    }

    static void solve() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        dfs(1, arr[0]);
    }

    static void dfs(int idx, int sum) {
        if (idx == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0 ; i < 4 ; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                dfs(idx+1, calculate(sum, arr[idx], i));
                operators[i]++;
            }
        }
    }

    static int calculate(int num1, int num2, int operator) {
        if (operator == 0) return num1+num2;
        else if (operator == 1) return num1-num2;
        else if (operator == 2) return num1*num2;
        return num1 / num2;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < 4 ; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
        operators = new int[4];
    }
}