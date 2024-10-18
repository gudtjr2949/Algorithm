import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long X;
    static long[] arr, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = "";

        while((input = bf.readLine()) != null) {
            X = Long.parseLong(input) * 10_000_000;
            N = Integer.parseInt(bf.readLine());
            arr = new long[N];
            answer = new long[2];
            for (int i = 0 ; i < N ; i++) {
                arr[i] = Long.parseLong(bf.readLine());
            }

            solve();

            if (answer[0] == 0 && answer[1] == 0) {
                sb.append("danger").append("\n");
            } else {
                sb.append("yes").append(" ").append(answer[0]).append(" ").append(answer[1]).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void solve() {
        Arrays.sort(arr);

        long left = 0;
        long right = N-1;

        while (left < right) {
            long result = arr[(int) left] + arr[(int) right];

            if (result == X) {
                answer[0] = arr[(int) left];
                answer[1] = arr[(int) right];
                break;
            }

            if (result > X) right--;
            else left++;
        }
    }
}