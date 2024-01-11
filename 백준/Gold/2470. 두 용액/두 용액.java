import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long min, answer1, answer2;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        arr = new long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        min = Long.MAX_VALUE;

        solve();

        System.out.println(answer1 + " " + answer2);
    }

    static void solve() {
        int left = 0;
        int right = N-1;

        while (left < right) {
            long sum = arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer1 = arr[left];
                answer2 = arr[right];
            }

            if (sum < 0) left++;
            else right--;
        }
    }
}