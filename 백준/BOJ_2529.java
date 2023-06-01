package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2529 : 부등호
public class BOJ_2529 {

    static int k;
    static char[] arr;
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(bf.readLine());
        arr = new char[k];

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        for (int i = 0; i < k; i++) {
            arr[i] = s_arr[i].charAt(0);
        }

        recursive(new int[k+1], new boolean[10], 0);

        double n = Math.pow(10, k);

        // 최댓값
        if (max / (long)n == 0) { // 젤 앞에 0이 오는 경우
            System.out.print(0);
        }

        System.out.println(max);

        // 촤솟값
        if (min / (long)n == 0) { // 젤 앞에 0이 오는 경우
            System.out.print(0);
        }

        System.out.println(min);

    }

    // 순열 만들기
    private static void recursive(int[] sel, boolean[] visited, int idx) {
        if (idx == sel.length) {

            if (check(sel)) {
                long sum = 0;

                for (int i = 0 ; i < sel.length ; i++) {
                    sum *= 10;
                    sum += sel[i];
                }

                max = Math.max(max, sum);
                min = Math.min(min, sum);
            }

            return;
        }

        for (int i = 0 ; i < 10 ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sel[idx] = i;
                recursive(sel, visited, idx + 1);
                visited[i] = false;
            }
        }
    }

    // 부등호와 sel 배열이 일치하는지 확인
    private static boolean check(int[] sel) {

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == '<') {
                if (sel[i] > sel[i+1]) {
                    return false;
                }
            }
            else {
                if (sel[i] < sel[i+1]) {
                    return false;
                }
            }
        }

        return true;
    }
}