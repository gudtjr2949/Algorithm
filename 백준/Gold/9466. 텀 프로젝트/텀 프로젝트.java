import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, completed;
    static int[] arr;
    static boolean[] visited, cycle;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            input(bf);
            solve();
            sb.append(N-completed).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }

    static void dfs(int now) {
        int next = arr[now];

        if (!visited[next]) {
            visited[next] = true;
            dfs(next);
        } else if (!cycle[next]) { // 싸이클 발견
            completed++;
            for (int i = next ; i != now ; i = arr[i]) {
                completed++;
            }
        }

        cycle[now] = true;
    }

    static void input(BufferedReader bf) throws Exception {
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        completed = 0;
        arr = new int[N+1];
        visited = new boolean[N+1];
        cycle = new boolean[N+1];
    }
}