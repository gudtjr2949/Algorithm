import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N+1];
        int[] prefixSum = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        arr[1] = Integer.parseInt(st.nextToken());

        prefixSum[1] = arr[1];

        for (int i = 2 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1] + arr[i];
        }

        int answer = 0;

        for (int i = 2 ; i < N ; i++) {
            answer = Math.max(answer, (prefixSum[N] - arr[1] - arr[i]) + (prefixSum[N] - prefixSum[i]));
        }

        for (int i = 2 ; i < N ; i++) {
            answer = Math.max(answer, (prefixSum[i] - arr[1]) + (prefixSum[N] - arr[N] - prefixSum[i-1]));
        }

        for (int i = 2 ; i < N ; i++) {
            answer = Math.max(answer, (prefixSum[i] - arr[i]) + (prefixSum[N] - arr[N] - arr[i]));
        }

        System.out.println(answer);
    }
}