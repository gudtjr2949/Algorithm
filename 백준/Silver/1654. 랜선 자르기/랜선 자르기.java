import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int K, N, MAX;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];
        for (int i = 0 ; i < K ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            MAX = Math.max(MAX, arr[i]);
        }

        solve();

        System.out.println(answer);
    }

    private static void solve() {
        long left = 0;
        long right = MAX;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = findLine(mid);

            if (cnt < N) {
                right = mid-1;
            } else {
                answer = mid;
                left = mid+1;
            }
        }
    }

    private static long findLine(long mid) {
        long cnt = 0;

        if (mid == 0) return N+1;

        for (int i = 0 ; i < K ; i++) {
            cnt += arr[i] / mid;
        }

        return cnt;
    }
}