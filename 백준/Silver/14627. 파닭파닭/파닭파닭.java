import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int S, C;
    static int[] arr; // 파

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[S];

        for (int i = 0 ; i < S ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        long result = binarySearch();

        long sum = 0;

        for (int n : arr)
            sum += n;

        long answer = sum - (result * C);

        System.out.println(answer);
    }

    static long binarySearch() {
        long left = 1;
        long right = 0;

        long result = 0;

        for (int i = 0 ; i < S ; i++)
            right += arr[i];

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid) >= C) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static int check(long mid) {
        int cnt = 0;

        for (int i = 0 ; i < S ; i++) {
            cnt += arr[i] / mid;
        }

        return cnt;
    }
}