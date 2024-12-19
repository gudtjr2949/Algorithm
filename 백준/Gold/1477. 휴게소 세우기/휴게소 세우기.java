import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L, MAX, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N+2];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;

        Arrays.sort(arr);

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch() {
        int left = 1;
        int right = L;

        while (left < right) {
            int mid = (left + right) / 2;

            // 가장 작은 mid 를 찾아야 함
            if (buildStation(mid) <= M) {
                answer = mid;
                right = mid;
            } else {
                left = mid+1;
            }
        }
    }

    static int buildStation(int mid) {
        int cnt = 0;

        for (int i = 1 ; i < arr.length ; i++) {
            int dist = arr[i] - arr[i-1];
            cnt += (dist / mid);

            if (dist % mid == 0) cnt--;
        }

        return cnt;
    }
}