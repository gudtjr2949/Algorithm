import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[ ] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
            if (arr[i] > right) right = arr[i];
        }

        while (left <= right) {
            int mid = (left + right) / 2; // 각 구간의 점수

            if (solve(mid) > M) { // 구간의 수가 너무 많음
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    static int solve(int mid) {
        int min = arr[0];
        int max = arr[0];

        int cnt = 1; // 구간 수

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

            if (arr[i] < min) {
                min = arr[i];
            }

            if (max - min > mid) {
                cnt++;
                max = arr[i];
                min = arr[i];
            }
        }

        return cnt;
    }
}