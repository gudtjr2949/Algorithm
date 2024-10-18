import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, pCnt, nCnt;
    static long answer;
    static long[] arr, positive, negative;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        arr = new long[N];

        for (int i = 0 ; i < N ; i++) {
            long num = Long.parseLong(bf.readLine());

            if (num > 1) pCnt++;
            else if (num <= 0) nCnt++;

            arr[i] = num;
        }

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        positive = new long[pCnt];
        negative = new long[nCnt];

        int pIdx = 0, nIdx = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] > 1) positive[pIdx++] = arr[i];
            else if (arr[i] == 1) answer++;
            else negative[nIdx++] = arr[i];
        }

        for (int i = pCnt-1 ; i >= 0 ; i -= 2) {
            if (i == 0) {
                answer += positive[i];
                break;
            }
            answer += (positive[i] * positive[i-1]);
        }

        for (int i = 0 ; i < nCnt ; i += 2) {
            if (i == nCnt-1) {
                answer += negative[i];
                break;
            }
            answer += (negative[i] * negative[i+1]);
        }
    }
}