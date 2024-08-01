import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int start = 0;
        int end = 0;

        int oddCnt = 0;

        while (end < N && start <= end) {
            if (oddCnt > K) {
                if (arr[start] % 2 != 0) oddCnt--;
                start++;
            } else {
                if (arr[end] % 2 != 0) oddCnt++;
                answer = Math.max(answer, end - start + 1 - oddCnt);
                end++;
            }
        }
    }
}