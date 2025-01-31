import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = N-1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(answer) > Math.abs(sum)) answer = sum;

            if (sum < 0) left++;
            else right--;
        }

    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
        answer = 200_000_001;
    }
}