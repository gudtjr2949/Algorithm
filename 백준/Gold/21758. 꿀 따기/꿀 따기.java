import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr, prefix;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        prefix = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        prefix[1] = arr[0];

        for (int i = 2 ; i <= N ; i++) {
            prefix[i] = prefix[i-1] + arr[i-1];
        }

        for (int i = 2 ; i < N ; i++) {
            bee_honey_bee(i);
            bee_bee_honey(i);
            honey_bee_bee(i);
        }

        System.out.println(answer);
    }

    static void bee_honey_bee(int start) {
        int left = 1; // 왼쪽 벌
        int right = N; // 오른쪽 벌
        int honey = start; // 꿀

        int sum = prefix[honey] - prefix[left] + prefix[right-1] - prefix[honey] + arr[start-1];

        answer = Math.max(answer, sum);
    }

    static void bee_bee_honey(int start) {
        int left = 1;
        int right = start;
        int honey = N;

        int sum = prefix[honey] - prefix[left] + prefix[honey] - prefix[right] - arr[start-1];

        answer = Math.max(answer, sum);
    }

    static void honey_bee_bee(int start) {
        int left = start;
        int right = N;
        int honey = 1;

        int sum = prefix[left-1] - prefix[honey-1] + prefix[right-1] - prefix[honey-1] - arr[start-1];

        answer = Math.max(answer, sum);
    }
}