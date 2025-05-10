import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        int left = 0;
        int right = 0;

        while (left < N && right < N) {
            if (arr[right] - arr[left] < M) {
                right++;
            } else {
                if (arr[right] - arr[left] == M) {
                    answer = M;
                    break;
                }
                answer = Math.min(answer, arr[right] - arr[left]);
                left++;
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
    }

    static void init() {
        arr = new int[N];
        answer = Integer.MAX_VALUE;
    }
}