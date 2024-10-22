import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N+2];

        st = new StringTokenizer(bf.readLine());

        arr[0] = 0;
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 1;
        int right = L-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (buildRest(mid) > M) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid;
            }
        }
    }

    static int buildRest(int mid) {
        int cnt = 0;

        for (int i = 1 ; i <= N+1 ; i++) {
            if ((arr[i] - arr[i-1]) % mid == 0) cnt += ((arr[i] - arr[i-1]) / mid) - 1;
            else cnt += ((arr[i] - arr[i-1]) / mid);
        }

        return cnt;
    }
}