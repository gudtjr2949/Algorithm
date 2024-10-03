import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, gender, idx;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            gender = Integer.parseInt(st.nextToken());
            idx = Integer.parseInt(st.nextToken());
            solve();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N ; i++) {
            sb.append(arr[i]).append(" ");
            if (i % 20 == 0) sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        if (gender == 1) {
            for (int i = idx ; i <= N ; i+= idx) {
                if (arr[i] == 0) arr[i] = 1;
                else arr[i] = 0;
            }
        } else {
            int left = idx;
            int right = idx;
            if (arr[idx] == 0) arr[idx] = 1;
            else arr[idx] = 0;

            while (true) {
                left--;
                right++;

                if (left < 1 || right > N)
                    break;

                if (arr[left] == arr[right]) {
                    if (arr[left] == 1) {
                        arr[left] = 0;
                        arr[right] = 0;
                    }
                    else {
                        arr[left] = 1;
                        arr[right] = 1;
                    }
                }
                else {
                    break;
                }
            }
        }
    }
}