import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = 1_000_000_001;

        while (left < right) {
            long mid = (left + right) / 2;

            if (countHub(mid) < C) {
                right = mid;
            } else {
                answer = mid;
                left = mid+1;
            }
        }
    }

    static int countHub(long mid) {
        int idx = 0;
        int cnt = 1;

        for (int i = 1 ; i < N ; i++) {
            if (arr[i] - arr[idx] >= mid) {
                idx = i;
                cnt++;
            }
        }

        return cnt;
    }
}