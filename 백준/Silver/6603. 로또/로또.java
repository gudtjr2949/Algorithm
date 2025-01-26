import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int K, LOTTO_LENGTH = 6;
    static int[] arr, input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }

    static void solve() {
        Arrays.sort(arr);
        dfs(0, 0);
    }

    static void dfs(int idx, int cur) {
        if (idx == LOTTO_LENGTH) {
            print();
            return;
        }

        for (int i = cur ; i < K ; i++) {
            input[idx] = arr[i];
            dfs(idx+1, i+1);
        }
    }

    static void print() {
        for (int i = 0 ; i < LOTTO_LENGTH ; i++) sb.append(input[i]).append(" ");
        sb.append("\n");
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            init();

            for (int i = 0 ; i < K ; i++) arr[i] = Integer.parseInt(st.nextToken());

            solve();

            sb.append("\n");
        }
    }

    static void init() {
        arr = new int[K];
        input = new int[LOTTO_LENGTH];
    }
}