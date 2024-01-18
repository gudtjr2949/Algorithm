import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static long N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(bf.readLine());
        K = Long.parseLong(bf.readLine());

        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long left = 1;
        long right = K;

        while (left < right) {
            long mid = (left + right) / 2;
            if (cntSum(mid) >= K) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // num 보다 작거나 같은 수가 배열 B에 최소 몇 개 있는지
    static long cntSum(long num) {
        long sum = 0;

        for (int i = 1 ; i <= N ; i++) {
            sum += Math.min(num / i, N);
        }

        return sum;
    }
}