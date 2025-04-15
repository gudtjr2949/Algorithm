import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX = 0, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = MAX;

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = calMoney(mid);
            if (sum <= M) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        answer = right;
    }

    static int calMoney(int mid) {
        int sum = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] > mid) {
                sum += mid;
            } else {
                sum += arr[i];
            }
        }

        return sum;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, arr[i]);
        }
        M = Integer.parseInt(bf.readLine());
    }

    static void init() {
        arr = new int[N];
    }
}