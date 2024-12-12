import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer, H, W;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < W ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i < W-1 ; i++) {
            int left = 0;

            for (int j = 0 ; j < i ; j++) {
                left = Math.max(left, arr[j]);
            }

            int right = 0;

            for (int j = i+1 ; j < W ; j++) {
                right = Math.max(right, arr[j]);
            }

            if (arr[i] < left && arr[i] < right) answer += Math.min(left, right) - arr[i];
        }
    }
}