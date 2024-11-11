import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, arr[i]);
        }

        solve();

        System.out.println(answer);
    }

    private static void solve() {
        long left = MAX;
        long right = Integer.MAX_VALUE;

        while (left < right) {
            long mid = (left + right) / 2;

            if (countCD(mid) > M)
                left = mid+1;
            else
                right = mid;
        }

        answer = left;
    }

    private static long countCD(long mid) {
        int cnt = 0;
        long sum = 0;

        for (int i = 0 ; i < N ; i++) {
            if (sum + arr[i] > mid) {
                cnt++;
                sum = 0;
            }
            sum += arr[i];
        }

        if (sum != 0) cnt++;

        return cnt;
    }
}