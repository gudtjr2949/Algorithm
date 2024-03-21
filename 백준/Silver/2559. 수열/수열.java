import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] sum = new int[N + 1];
        int answer = Integer.MIN_VALUE;
        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            answer = Math.max(answer, arr[i]);
        }

        if (K == 1) {
            System.out.println(answer);
        } else {
            answer = Integer.MIN_VALUE;

            sum[1] = arr[1];

            for (int i = 2; i <= N; i++) {
                sum[i] = arr[i] + sum[i - 1];
            }

            for (int i = K ; i <= N ; i++) {
                answer = Math.max(answer, sum[i] - sum[i - K]);
            }

            System.out.println(answer);
        }
    }
}