import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX, SUM, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int left = MAX;
        int right = SUM;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (findCnt(mid) > M) {
                left = mid+1;
            } else {
                right = mid-1;
                answer = mid;
            }
        }
    }

    // 인출 횟수 찾기
    static int findCnt(int mid) {
        int cnt = 1;
        int now = mid; // 현재 가지고 있는 금액

        for (int i = 0 ; i < N ; i++) {
            now -= arr[i];

            // 돈이 모자라기 때문에 인출했어야 함
            if (now < 0) {
                now = mid - arr[i];
                cnt++;
            }
        }

        return cnt;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            SUM += arr[i];
            MAX = Math.max(MAX, arr[i]);
        }
    }

    static void init() {
        arr = new int[N];
    }
}