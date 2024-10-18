import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX, answer;
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

    static void solve() {
        int left = 0;
        int right = MAX;

        while (left < right) {
            int mid = (left + right) / 2;

            if (countRange(mid) > M) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid;
            }
        }
    }

    static int countRange(int mid) {
        int min = arr[0];
        int max = arr[0];
        int cnt = 1;

        for (int i = 0 ; i < N ; i++) {
            if (min > arr[i]) min = arr[i];
            if (max < arr[i]) max = arr[i];

            if (max - min > mid) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }

        return cnt;
    }
}