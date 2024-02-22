import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[N];

        long sum = 0;

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            sum += arr[i];
        }

        long answer = 0;
        long remain = (long) Math.pow(2, 64);

        if (M >= sum) {
            System.out.println(0);
        } else {
            long lack = sum - M;
            Arrays.sort(arr);
            for (int i = 0 ; i < N ; i++) {
                // 각 인원들이 못받는 사탕의 양을 최대한 균등하게 해야 함 -> Math.min(아예 사탕을 못받은 경우, 다른 사람들이랑 똑같은 양만큼 못받는 경우)
                long notReceive = Math.min(arr[i], lack / (N - i));
                lack -= notReceive;
                answer += notReceive * notReceive;
            }
        }

        answer %= remain;

        System.out.println(answer);
    }
}