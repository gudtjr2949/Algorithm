import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        dfs(0, 0);

        if (answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (cur == K) {
            answer = idx;
            return;
        }

        if (!visited[arr[cur]]) {
            visited[arr[cur]] = true;
            dfs(idx + 1, arr[cur]);
        }
    }
}