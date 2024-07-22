import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = Integer.MAX_VALUE;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];

        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(bf.readLine());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(A);

        int left = 0;
        int right = 1;

        while (left <= right && right < N && left < N) {
            int diff = A[right] - A[left];

            if (diff < M) {
                right++;
            } else {
                left++;
                answer = Math.min(answer, diff);

                if (answer == M) break;
            }
        }
    }
}