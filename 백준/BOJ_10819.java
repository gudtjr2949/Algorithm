package coding_test.백준;

import java.io.*;
import java.util.ArrayList;

// 백준 10819 : 차이를 최대로
public class BOJ_10819 {

    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        boolean[] visited = new boolean[N];

        int[] output = new int[N];

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(s_arr[i]);
        }

        perm(arr, output, visited, 0, N, N); // arr 배열에서 N개 중 N개 뽑기

        System.out.println(max);
    }

    private static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) { // 만약에 끝까지 탐색했으면
            int sum = 0;

            for (int i = 0 ; i < r-1 ; i++) {
                sum += Math.abs(output[i] - output[i+1]);
            }

            max = Math.max(max, sum);

            return;
        }

        for (int i = 0 ; i < n ; i++) {
            if (visited[i] != true) { // 만약에 거기 처음 방문했으면
                visited[i] = true; // 방문 표시
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r);
                visited[i] = false;;
            }
        }
    }
}
