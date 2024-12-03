import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, answer = Integer.MAX_VALUE;
    static int[] prefix;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        prefix = new int[N+1];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i+1] = prefix[i] + arr[i];
        }

        solve();

        if (answer == Integer.MAX_VALUE) answer = 0;

        System.out.println(answer);
    }

    static void solve() {
        int left = 1;
        int right = 1;

        while (left <= right && right <= N) {
            int sum = prefix[right] - prefix[left-1];

            if (sum < S) {
                right++;
            } else {
                answer = Math.min(answer, right - left + 1);
                left++;
            }
        }
    }
}