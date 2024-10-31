import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(bf.readLine());

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = Integer.MAX_VALUE;

        while (left < right) {
            int mid = (left + right) / 2;

            if (countHub(mid) < M) {
                right = mid;
            } else {
                answer = mid;
                left = mid+1;
            }
        }
    }

    static int countHub(int mid) {
        int cnt = 1;
        int idx = 0;

        for (int i = 1 ; i < N ; i++) {
            if (arr[i] - arr[idx] >= mid) {
                idx = i;
                cnt++;
            }
        }

        return cnt;
    }
}