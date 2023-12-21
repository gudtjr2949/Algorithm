import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] arr, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        solve();

        for (int i = 0; i < 3; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void solve() {
        long nearZero = 0;
        answer = new long[3];

        nearZero = Long.MAX_VALUE;

        Loop:
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                if (nearZero > Math.abs(sum)) {
                    nearZero = Math.abs(sum);
                    answer[0] = arr[i];
                    answer[1] = arr[left];
                    answer[2] = arr[right];
                }

                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }

                if (nearZero == 0) {
                    break Loop;
                }
            }
        }
    }
}