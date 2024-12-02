import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, M, MIN = Integer.MAX_VALUE;
    static long answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        arr = new long[(int) N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(bf.readLine());
            MIN = Math.min(MIN, arr[i]);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = M * MIN;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid) < M) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        answer = left;
    }

    static long check(long mid) {
        long result = 0;

        for (int i = 0 ; i < arr.length ; i++) {
            result += mid / arr[i];

            if (result > M) return M+1;
        }

        return result;
    }
}