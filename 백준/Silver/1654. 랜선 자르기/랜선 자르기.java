import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long K, N, MAX, answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Long.parseLong(st.nextToken());
        N = Long.parseLong(st.nextToken());

        arr = new long[(int) K];

        for (int i = 0 ; i < K ; i++) {
            arr[i] = Long.parseLong(bf.readLine());
            MAX = Math.max(MAX, arr[i]);
        }

        solve();
        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = MAX;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = cntLine(mid);
            if (cnt >= N) {
                left = mid+1;
                answer = Math.max(answer, mid);
            } else {
                right = mid-1;
            }
        }
    }

    static long cntLine(long mid) {
        if (mid == 0) return N+1;
        long result = 0;
        for (int i = 0 ; i < K ; i++) {
            result += arr[i] / mid;
        }
        return result;
    }
}