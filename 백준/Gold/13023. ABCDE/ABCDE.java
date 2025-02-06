import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean possible;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(possible ? 1 : 0);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, 1);
            if (possible) break;
        }
    }

    static void dfs(int idx, int length) {
        if (length == 5) {
            possible = true;
            return;
        }

        if (possible) return;

        for (int next : adj.get(idx)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, length+1);
                visited[next] = false;
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
    }

    static void init() {
        visited = new boolean[N];
        adj = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) adj.add(new ArrayList<>());
    }
}