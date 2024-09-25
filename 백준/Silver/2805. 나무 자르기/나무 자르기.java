import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, M, MAX, answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        arr = new long[(int) N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            MAX = Math.max(MAX, arr[i]);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = MAX;

        while (left < right) {
            long mid = (left + right) / 2;
            long cnt = cntTree(mid);

            if (cnt >= M) {
                left = mid+1;
                answer = Math.max(answer, mid);
            } else {
                right = mid;
            }
        }
    }

    static long cntTree(long mid) {
        long result = 0;
        for (int i = 0 ; i < N ; i++) {
            result += (arr[i] - mid) > 0 ? arr[i] - mid : 0;
        }
        return result;
    }
}