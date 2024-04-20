import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static long MIN = 1_000_000, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch() {
        long left = 0;
        long right = MIN * M;

        while (left <= right) {
            long mid = (left + right) / 2;

            // 만들 수 있는 풍선의 갯수가 너무 많음 -> 시간을 줄여야 함
            if (solve(mid) >= M) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    // mid 만큼 시간이 주어졌을 때, 만들 수 있는 풍선 갯수
    static long solve(long mid) {
        long cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            cnt += mid / arr[i];
        }

        return cnt;
    }
}