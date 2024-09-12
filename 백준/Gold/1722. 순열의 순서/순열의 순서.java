import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, num;
    static long K;
    static int[] arr;
    static long[] fac;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        fac = new long[N+1];
        visited = new boolean[N+1];

        fac[0] = 1;
        for (int i = 1 ; i <= N ; i++) fac[i] = fac[i-1] * i;

        StringTokenizer st = new StringTokenizer(bf.readLine());

        num = Integer.parseInt(st.nextToken());
        if (num == 1) {
            K = Long.parseLong(st.nextToken());
            solve1();
        }
        else {
            arr = new int[N];
            for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(st.nextToken());
            solve2();
        }
    }

    static void solve1() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (visited[j]) continue;
                if(fac[N-i-1] < K) K -= fac[N-i-1];
                else {
                    list.add(j);
                    visited[j] = true;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) sb.append(num).append(" ");
        System.out.println(sb);
    }

    static void solve2() {
        long result = 1L;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 1 ; j < arr[i] ; j++) {
                if (!visited[j]) {
                    result += fac[N-i-1];
                }
            }
            visited[arr[i]] = true;
        }

        System.out.println(result);
    }
}