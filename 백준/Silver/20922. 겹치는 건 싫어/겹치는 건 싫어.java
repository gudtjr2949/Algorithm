import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] arr, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(bf.readLine());

        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        cnt = new int[max+1];

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int start = 0;
        int end = 0;

        while (end < N && start <= end) {
            if (cnt[arr[end]] >= K) {
                cnt[arr[start]]--;
                start++;
            } else {
                cnt[arr[end]]++;
                answer = Math.max(answer, end - start + 1);
                end++;
            }
        }
    }
}