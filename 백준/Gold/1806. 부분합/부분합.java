import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        answer = Integer.MAX_VALUE;

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i] == S) {
                answer = 1;
            }
        }

        solve();

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    static void solve() {
        int left = 0;
        int right = 0;

        int prefixSum = 0;

        while (left <= N && right <= N) {
            if (prefixSum >= S) {
                answer = Math.min(answer, right - left);
                prefixSum -= arr[left++];
            } else {
                prefixSum += arr[right++];
            }
        }
    }
}