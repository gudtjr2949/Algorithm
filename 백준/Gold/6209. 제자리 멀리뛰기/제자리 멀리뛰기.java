import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int D, N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+2];

        arr[0] = 0;
        arr[N+1] = D;

        for (int i = 1 ; i <=N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        binaySearch();
    }

    static void binaySearch() {
        int left = 0;
        int right = D;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (solve(mid) > M) { // 너무 많이 제거함
                // 더 적게 제거하기 위해선 mid 가 작아져야 함
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.println(left-1);
    }

    // 제거한 돌 수
    static int solve(int mid) {
        int cnt = 0;
        int now = 0;

        for(int i = 1 ; i <= N ; i++){
            if (arr[i] - arr[now] < mid){ // 두 돌 사이의 거리가 mid 보다 작은 경우 -> 해당 돌 제거함
                cnt++;
            } else { // 두 돌 사이의 거리가 mid 보다 크거나 같은 경우 -> 해당 돌로 이동
                now = i;
            }
        }

        return cnt;
    }
}