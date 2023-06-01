package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 15655 : N과 M (6)
public class BOJ_15655 {
    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        N = Integer.parseInt(s_arr[0]);
        M = Integer.parseInt(s_arr[1]);

        arr = new int[N];

        String s2 = bf.readLine();
        String[] s2_arr = s2.split(" ");

        for (int i = 0 ; i < s2_arr.length ; i++)
            arr[i] = Integer.parseInt(s2_arr[i]);

        Arrays.sort(arr);

        recursive(new int[M], new boolean[N], 0, 0);

    }

    private static void recursive(int[] sel, boolean[] visited, int idx, int cur) {
        if (idx == M) {
            print(sel);
            return;
        }

        for (int i = cur ; i < N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sel[idx] = arr[i];
                recursive(sel, visited, idx + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static void print(int[] sel) {
        for (int i = 0 ; i < sel.length ; i++){
            System.out.print(sel[i] + " ");
        }
        System.out.println();
    }
}
