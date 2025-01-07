import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        int idx = 0;
        Integer[] dist = new Integer[N-1];
        for (int i = 1 ; i < N ; i++) {
            dist[idx] = arr[i] - arr[i-1];
            answer += dist[idx++];
        }

        Arrays.sort(dist, Collections.reverseOrder());

        idx = 0;

        while (idx < K-1 && idx < dist.length) {
            answer -= dist[idx++];
        }
    }
}