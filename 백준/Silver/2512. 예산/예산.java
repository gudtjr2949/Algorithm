import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, max = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int sum = 0;
        max = 0;

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        M = Integer.parseInt(bf.readLine());

        if (sum <= M) {
            System.out.println(max);
        } else {
            binarySearch();
        }
    }

    static void binarySearch() {
        int left = 0;
        int right = max;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 각 도시에 나눠줄 예산

            if (solve(mid) > M) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static int solve(int mid) {
        int sum = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] <= mid) {
                sum += arr[i];
            } else {
                sum += mid;
            }
        }

        return sum;
    }
}