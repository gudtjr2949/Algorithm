import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;
    static Integer[] diff;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());
        arr = new int[N];
        diff = new Integer[N-1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(solve());
    }

    static int solve() {
        for (int i = 0 ; i < N-1 ; i++) {
            diff[i] = Math.abs(arr[i+1] - arr[i]);
        }

        Arrays.sort(diff, Comparator.reverseOrder());

        int answer = 0;

        for (int i = K-1 ; i < N-1 ; i++) {
            answer += diff[i];
        }

        return answer;
    }
}