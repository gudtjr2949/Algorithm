package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 3040 : 백설 공주와 일곱 난쟁이
public class BOJ_3040 {

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9];

        for (int i = 0 ; i < 9 ; i++)
            arr[i] = Integer.parseInt(bf.readLine());

        solve(new int[7], 0, 0);
    }

    private static void solve(int[] input, int idx, int cur) {
        if (idx == input.length) {
            if (Find_Sum(input) == 100) {
                for (int i = 0 ; i < input.length ; i++) {
                    System.out.println(input[i]);
                }
            }
            return;
        }

        for (int i = cur ; i < arr.length ; i++) {
            input[idx] = arr[i];
            solve(input, idx + 1 , i + 1);
        }
    }

    private static int Find_Sum(int[] input) {
        int sum = 0;

        for (int i = 0 ; i < input.length ; i++) {
            sum += input[i];
        }

        return sum;
    }
}
