package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1339 : 단어 수학
public class BOJ_1339 {

    static int N;
    static String[] arr;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new String[N];

        answer = 0;

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            arr[i] = s;
        }

        solve();

        System.out.println(answer);
    }

    private static void solve() {

        long[] sumArr = new long[26];

        for (int i = 0 ; i < N ; i++) {
            int n = 0;
            for (int j = arr[i].length()-1 ; j >= 0 ; j--) {
                sumArr[arr[i].charAt(j) - 'A'] += 1 * Math.pow(10, n);
                n++;
            }
        }

        Arrays.sort(sumArr);

        long sum = 0;

        int idx = 9;

        for (int i = 25 ; i >= 0 ; i--) {
            sum += sumArr[i] * idx;
            idx--;

            if (idx < 0)
                break;
        }

        answer = sum;
    }
}
