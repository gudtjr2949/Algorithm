import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, X, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        X = Integer.parseInt(bf.readLine());

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int start = 0;
        int end = N-1;

        while (start < end) {
            int sum = arr[start] + arr[end];

            if (sum <= X) {
                if (sum == X) answer++;
                start++;
            } else end--;
        }
    }
}