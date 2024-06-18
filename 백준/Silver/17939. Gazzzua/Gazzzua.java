import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            max = Math.max(max, arr[i]);
        }

        int sum = 0;
        int cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] < max) {
                sum += arr[i];
                cnt++;
            } else {
                answer += (arr[i] * cnt) - sum;
                sum = 0;
                cnt = 0;
                max = 0;
                for (int j = i+1 ; j < N ; j++) {
                    max = Math.max(max, arr[j]);
                }
            }
        }
    }
}