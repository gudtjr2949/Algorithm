import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] depth, parents;
    static boolean[] visited;
    static int[][] operations;
    static List<List<Integer>> adj;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        visited[1] = true;
        setDepth(1);

        for (int[] operation : operations) {
            int a = operation[0];
            int b = operation[1];

            if (depth[a] > depth[b]) {
                while (depth[a] != depth[b]) {
                    a = parents[a];
                }
            } else if (depth[a] < depth[b]) {
                while (depth[a] != depth[b]) {
                    b = parents[b];
                }
            }


            while (a != b) {
                a = parents[a];
                b = parents[b];
            }

            sb.append(a).append("\n");
        }
    }

    static void setDepth(int idx) {
        for (int next : adj.get(idx)) {
            if (!visited[next]) {
                visited[next] = true;
                depth[next] = depth[idx]+1;
                parents[next] = idx;
                setDepth(next);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        init1();
        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        M = Integer.parseInt(bf.readLine());
        init2();
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            operations[i][0] = a;
            operations[i][1] = b;
        }
    }

    static void init1() {
        depth = new int[N+1];
        parents = new int[N+1];
        visited = new boolean[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
    }

    static void init2() {
        operations = new int[M][2];
    }
}