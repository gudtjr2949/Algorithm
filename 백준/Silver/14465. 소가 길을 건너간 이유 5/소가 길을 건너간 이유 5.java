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
        int B = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        for (int i = 0 ; i < B ; i++) {
            arr[Integer.parseInt(bf.readLine())] = 1;
        }

        for (int i = 1 ; i <= N ; i++) {
            arr[i] += arr[i-1];
        }

        int answer = Integer.MAX_VALUE;

        for (int i = K+1 ; i <= N ; i++) {
            answer = Math.min(answer, arr[i] - arr[i-K]);
        }

        System.out.println(answer);
    }
}