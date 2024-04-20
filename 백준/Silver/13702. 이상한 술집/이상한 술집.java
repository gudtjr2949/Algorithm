import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long max, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch() {
        long left = 0;
        long right = Integer.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            // 나눠줄 수 있는 친구의 수가 너무 적음 -> 각 친구에게 나눠주는 막걸리의 양을 줄여야 함
            if (solve(mid) < K) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid;
            }
        }
    }

    // mid 만큼의 막걸리를 각각의 친구들에게 나눠줬을 때, 나눠줄 수 있는 친구의 수
    static long solve(long mid) {
        long cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            cnt += arr[i] / mid;
        }

        return cnt;
    }
}