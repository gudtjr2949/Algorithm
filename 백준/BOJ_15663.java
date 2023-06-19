package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 15663 : N과 M (9)
public class BOJ_15663 {

    static int N, M;
    static int[] arr;
    static LinkedHashSet<String> set;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];


        set = new LinkedHashSet<>();

        visited = new boolean[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, new int[M], new boolean[N]);

        Iterator<String> iter = set.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    private static void dfs(int idx, int[] input, boolean[] visited) {
        if (idx == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < M ; i++) {
                sb.append(input[i]).append(" ");
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 0 ; i < N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = arr[i];
                dfs(idx+1, input, visited);
                visited[i] = false;
            }
        }
    }
}
