import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        binarySearch();
    }

    static void binarySearch() {
        long left = 0;
        long right = N;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = (mid+1) * (N-mid+1);

            if (cnt > K) {
                right = mid-1;
            } else {
                if (cnt == K) {
                    System.out.println("YES");
                    return;
                }
                left = mid+1;
            }
        }

        System.out.println("NO");
    }
}