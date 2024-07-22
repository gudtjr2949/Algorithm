import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static long answer;
    static int[] arr, diffArr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        diffArr = new int[N-1];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    private static void solve() {
        for (int i = 0 ; i < N-1 ; i++) {
            diffArr[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(diffArr);

        for (int i = 0 ; i < N-K ; i++) {
            answer += diffArr[i];
        }
    }
}