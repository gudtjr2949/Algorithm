import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, idx;
    static long min;
    static boolean find;
    static long[] arr, answer;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

    static void solve() {
        Arrays.sort(arr);

        for (int i = 0 ; i < N-2 ; i++) {
            idx = i;
            bs(i+1, N-1);
            if (find) break;
        }
    }

    static void bs(int left, int right) {
        while (left < right) {
            long sum = arr[idx] + arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);

                answer[0] = arr[idx];
                answer[1] = arr[left];
                answer[2] = arr[right];

                if (min == 0L) {
                    find = true;
                    return;
                }
            }

            if (sum < 0) left++;
            else right--;
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
    }

    static void init() {
        arr = new long[N];
        answer = new long[3];
        min = 3_000_000_001L;
    }
}