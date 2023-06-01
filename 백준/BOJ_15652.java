package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 15652 : N과 M (4)
public class BOJ_15652 {

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        String[] s_arr = s.split(" ");

        N = Integer.parseInt(s_arr[0]);
        M = Integer.parseInt(s_arr[1]);

        recursive(new int[M], 0, 1);
    }

    private static void recursive(int[] sel, int idx, int cur) {
        if (idx == M) {
            print(sel);
            return;
        }

        for (int i = cur ; i <= N ; i++) {
            sel[idx] = i;
            recursive(sel, idx + 1, i);
        }

    }

    private static void print(int[] sel) {
        for (int i = 0 ; i < sel.length ; i++) {
            System.out.print(sel[i] + " ");
        }
        System.out.println();
    }
}
