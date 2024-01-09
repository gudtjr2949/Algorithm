import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, M, answer = 0;
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
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long low = 0;
        long high = 0;

        for (int i = 0 ; i < N ; i++) {
            high = Math.max(arr[i], high);
        }

        while (low < high) {
            long mid = (low + high) / 2;

            if (checkTreeLength(mid) >= M) { // 나무를 너무 많이 짜름 -> mid를 더 위로 올려야 함 -> low = mid
                low = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                high = mid;
            }
        }

    }

    static long checkTreeLength(long cut) {
        long sum = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] > cut) {
                sum += arr[i] - cut;
            }
        }

        return sum;
    }
}