import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr = new int[N];
            for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            sb.append(solve()).append("\n");
        }

        System.out.println(sb);
    }

    static int solve() {
        int[] tmp = new int[N];

        int left = 0;
        int right = N-1;

        for (int i = 0 ; i < N ; i++) {
            if (i % 2 == 0) {
                tmp[left] = arr[i];
                left++;
            } else {
                tmp[right] = arr[i];
                right--;
            }
        }

        int max = Math.abs(tmp[0] - tmp[N-1]);

        for (int i = 0 ; i < N-1 ; i++) {
            max = Math.max(Math.abs(tmp[i] - tmp[i+1]), max);
        }

        return max;
    }
}