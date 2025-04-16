import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static long answer;
    static long[] arr;
    static List<Long> diff;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        for (int i = 0 ; i < N-1 ; i++) {
            diff.add(arr[i+1] - arr[i]);
            answer += arr[i+1] - arr[i];
        }

        Collections.sort(diff, Collections.reverseOrder());

        for (int i = 0 ; i < K-1 ; i++) {
            answer -= diff.get(i);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
    }

    static void init() {
        arr = new long[N];
        diff = new ArrayList<>();
    }
}