import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, sum = 0;
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
            sum += arr[i];
        }

        binarySearch();
    }

    static void binarySearch() {
        int left = 1;
        int right = sum;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (solve(mid) > M) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static int solve(int mid) {
        int cnt = 1;
        int length = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] > mid) {
                return M+1;
            }

            if (length + arr[i] <= mid) {
                length += arr[i];
            } else {
                length = arr[i];
                cnt++;
            }
        }

        return cnt;
    }
}