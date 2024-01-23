import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static long[] arr;
    static int K, N;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[K];

        for (int i = 0 ; i < K ; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        Arrays.sort(arr);

        binarySearch();
    }

    static void binarySearch() {
        long left = 1;
        long right = Integer.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = cntLAN(mid);

            if (cnt < N) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
    }

    static long cntLAN(long num) {
        long cnt = 0;

        for (int i = 0 ; i < K ; i++) {
            cnt += arr[i] / num;
        }

        return cnt;
    }
}