import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        long left = 0;
        long right = Integer.MAX_VALUE;

        while (left < right) {
            long mid = (left + right) / 2;

            if (checkKettle(mid)) {
                answer = mid;
                left = mid+1;
            } else {
                right = mid;
            }
        }
    }

    static boolean checkKettle(long mid) {
        long result = 0;

        for (int i = 0 ; i < N ; i++) {
            result += arr[i] / mid;
        }

        if (result >= K) return true;

        return false;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(bf.readLine());
        }
    }

    static void init() {
        arr = new long[N];
    }
}