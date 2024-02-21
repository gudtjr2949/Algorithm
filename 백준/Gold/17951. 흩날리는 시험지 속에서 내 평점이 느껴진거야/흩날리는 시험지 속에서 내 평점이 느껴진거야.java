import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch();
    }

    static void binarySearch() {
        int left = 0;
        int right = 0;

        for (int i = 0 ; i < N ; i++) {
            right += arr[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            if (solve(mid) > K) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    // 각 구간의 합이 최소한 mid 는 넘겨야 함
    static int solve(int mid) {
        int sum = 0;
        int cnt = 1;

        for (int i = 0 ; i < N ; i++) {
            if (sum + arr[i] < mid) {
                sum += arr[i];
            } else { // sum + arr[i] 가 mid 보다 크거나 같아지면? -> 구간이 하나 생김
                sum = 0;
                cnt++;
            }
        }

        return cnt;
    }
}