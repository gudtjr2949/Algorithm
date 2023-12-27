import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        arr = new int[N];
        dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];

        int idx = 0;
        int len = 0;

        for (int i = 0 ; i < N ; i++) {
            if (dp[len] < arr[i]) {
                len++;
                dp[len] = arr[i];
            } else {
                idx = binarySearch(0, len, arr[i]);
                dp[idx] = arr[i];
            }
        }

        System.out.println(N - len - 1);
    }

    static int binarySearch(int start, int end, int num) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] > num) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }
}