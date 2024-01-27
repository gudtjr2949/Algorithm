import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        Integer[] arr = new Integer[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int max = arr[0];

        for (int i = 0 ; i < N ; i++) {
            int now = arr[i] * (i+1);

            if (now > max) {
                max = now;
            }
        }

        System.out.println(max);
    }
}