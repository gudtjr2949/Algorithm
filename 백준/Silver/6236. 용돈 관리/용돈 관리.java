import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, max = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            max = Math.max(max, arr[i]);
        }

        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int left = max;
        int right = 10_000 * 100_000;

        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int result = solve(mid);

            if (result > M) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }

    static int solve(int mid) {
        int cnt = 1;
        int money = mid;

        for (int i = 0 ; i < N ; i++) {
            money -= arr[i];

            if (money < 0) {
                cnt++;
                money = mid - arr[i];
            }
        }

        return cnt;
    }
}