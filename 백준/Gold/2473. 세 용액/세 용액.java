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
        answer = new long[3];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++)
            arr[i] = Long.parseLong(st.nextToken());


        Arrays.sort(arr);

        solve();

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

    static void solve() {
        long min = Long.MAX_VALUE;

        for (int i = 0 ; i < N-2 ; i++) {
            int left = i+1;
            int right = N-1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];

                if (min > Math.abs(sum)) {
                    answer[0] = arr[i];
                    answer[1] = arr[left];
                    answer[2] = arr[right];
                    min = Math.abs(sum);
                }


                if (sum < 0) {
                    left++;
                } else {
                    if (min == 0) return;
                    right--;
                }
            }
        }
    }
}