import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, M, L;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N+2];

        st = new StringTokenizer(bf.readLine());

        arr[0] = 0;

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        arr[N+1] = L;

        Arrays.sort(arr);

        solve();
    }

    static void solve() {
        int left = 1;
        int right = L-1;

        while (left <= right) {
            int mid = (left + right) / 2; // left 위치와 right 위치의 가운데 위치
            int sum = 0;

            for (int i = 1 ; i <= N+1 ; i++) {
                sum += (arr[i] - arr[i-1] - 1) / mid; // arr[i] 와 arr[i-1] 사이에 mid 만큼 나눴을 때, 총 길이
            }

            if (sum > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}