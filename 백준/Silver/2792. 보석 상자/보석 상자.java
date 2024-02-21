import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        binarySearch();
    }

    static void binarySearch() {
        int left = 0;
        int right = 0;

        for (int i = 0; i < M; i++) {
            right = Math.max(right, arr[i]);
        }

        while (left <= right) {
            // mid가 0이 되면 안됨
            int mid = (left + right) / 2;

            if (mid == 0) break;

            if (solve(mid) > N) { // 최대 mid 개 만큼 나눠주기 위해선, N보다 더 많은 인원이 필요함
                left = mid + 1;
            } else { // 못받은 아이가 존재할 수 있음 -> mid 를 더 작게해서 더 많은 아이에게 나눠줄 수 있음
                right = mid - 1;
            }
        }

        System.out.println(left == 0 ? 1 : left);
    }

    static int solve(int mid) {
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            if (arr[i] % mid == 0) {
                cnt += arr[i] / mid;
            } else {
                cnt += (arr[i] / mid) + 1;
            }
        }

        return cnt;
    }
}