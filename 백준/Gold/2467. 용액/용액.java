import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        answer = new int[2];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        solve();

        System.out.println(answer[0] + " " + answer[1]);
    }

    static void solve() {
        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (min > Math.abs(sum)) {
                answer[0] = arr[left];
                answer[1] = arr[right];
                min = Math.abs(sum);
            }


            if (sum < 0) left++;
            else {
                if (sum == 0) return;
                right--;
            }
        }
    }
}