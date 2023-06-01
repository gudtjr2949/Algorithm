package coding_test.백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 백준 1174 : 줄어드는 수
public class BOJ_1174 {

    static int N;
    static long[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static ArrayList<Long> list;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();

        N = Integer.parseInt(bf.readLine());

        if (N < 1024) {
            for (int i = 0; i < 10; i++)
                solve(0, i, new long[10 - i], new boolean[10]);

            Collections.sort(list);

            System.out.println(list.get(N - 1));
        }
        else {
            System.out.println(-1);
        }
    }

    private static void solve(int idx, int cur, long[] input, boolean[] visited) {
        if (idx == input.length) {
            if (!list.contains(make_long(input, idx)))
                list.add(make_long(input, idx)); // 마지막 털기
            return;
        }

        if (!list.contains(make_long(input, idx))) {
            list.add(make_long(input, idx));
        }

        for (int i = cur ; i < 10 ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = arr[i];
                solve(idx + 1, i + 1, input, visited);
                visited[i] = false;
            }
        }
    }

    private static long make_long(long[] input, int idx) {
        long num = 0;

        for (int i = 0 ; i < idx ; i++) {
            num += input[i] * Math.pow(10, (idx-i-1));
        }

        return num;
    }
}
