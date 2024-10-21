import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int num = arr[0];

        if (num != 1) {
            answer = 1;
            return;
        }

        for (int i = 1 ; i < N ; i++) {
            if (num + 1 < arr[i]) break;
            num += arr[i];
        }

        answer = num+1;
    }
}