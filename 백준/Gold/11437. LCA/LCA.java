import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<List<Integer>> tree;
    static int[] depth, parents;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        tree = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            tree.add(new ArrayList<>());
        }

        depth = new int[N+1];
        parents = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        visited[1] = true;
        dfs(1, 0);

        int M = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // b가 무조건 깊이가 깊음
            if (depth[a] > depth[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            solve(a, b);
        }

        System.out.println(sb);
    }

    static void solve(int a, int b) {
        int depthA = depth[a];
        int depthB = depth[b];

        if (depthA != depthB) {
            while (depthA != depthB) {
                b = parents[b];
                depthB = depth[b];
            }
        }

        while (a != b) {
            a = parents[a];
            b = parents[b];
        }

        sb.append(a).append("\n");
    }

    static void dfs(int idx, int floor) {
        for (int next : tree.get(idx)) {
            if (!visited[next]) {
                visited[next] = true;
                depth[next] = floor+1;
                parents[next] = idx;
                dfs(next, floor+1);
            }
        }
    }
}