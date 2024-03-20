import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long MAX = 0;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            MAX = Math.max(MAX, arr[i]);
        }

        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long left = 1;
        long right = MAX;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (solve(mid) < M) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }

    static long solve(long mid) {
        long cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] >= mid) {
                cnt += arr[i] / mid;
            }
        }

        return cnt;
    }
}