import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long N, H, min, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        H = Long.parseLong(st.nextToken());

        long[] arr1 = new long[(int) N/2];
        long[] arr2 = new long[(int) N/2];

        for (int i = 0 ; i < N ; i++) {
            if (i % 2 == 0) {
                arr1[i/2] = Long.parseLong(bf.readLine());
            } else {
                arr2[i/2] = Long.parseLong(bf.readLine());
            }
        }

        min = Integer.MAX_VALUE;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        solve(arr1, arr2);

        System.out.println(min + " " + cnt);
    }

    static void solve(long[] arr1, long[] arr2) {
        for (int i = 1 ; i <= H ; i++) {
            long sum = bs(0, N/2, i, arr1) + bs(0, N/2, H-i+1, arr2);

            if (sum < min) {
                min = sum;
                cnt = 1;
            } else if (sum == min) cnt++;
        }
    }

    static long bs(long left, long right, long target, long[] arr) {
        while (left < right) {
            long mid = (left + right) / 2;

            if (arr[(int) mid] < target) {
                left = mid+1;
            } else right = mid;
        }

        return arr.length - left;
    }
}