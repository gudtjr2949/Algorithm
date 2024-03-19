import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long N, M;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        arr = new long[(int) N];

        for (int i = 0 ; i < (int) N ; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }

        Arrays.sort(arr);

        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long left = 0;
        long right = M * arr[(int) N-1];
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0 ; i < N ; i++) {
                cnt += mid / arr[i];

                if (cnt >= M) break;
            }

            if (cnt < M) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }
}