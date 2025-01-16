import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, A, B;
    static List<List<Integer>> tree;
    static int[] depths, parents;
    static boolean[] visited, root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0 ) {
            N = Integer.parseInt(bf.readLine());
            init();
            input(bf);

            int rootNode = findRoot();

            depths[rootNode] = 1;
            visited[rootNode] = true;
            findDepth(rootNode, 1);

            sb.append(solve()).append("\n");
        }

        System.out.println(sb);
    }

    static int solve() {
        if (depths[A] > depths[B]) {
            while (depths[A] != depths[B]) A = parents[A];
        } else if (depths[A] < depths[B]) {
            while (depths[A] != depths[B]) B = parents[B];
        }

        while (A != B) {
            A = parents[A];
            B = parents[B];
        }

        return A;
    }

    static void findDepth(int idx, int depth) {
        for (int next : tree.get(idx)) {
            if (!visited[next]) {
                visited[next] = true;
                parents[next] = idx;
                depths[next] = depth+1;
                findDepth(next, depth+1);
            }
        }
    }

    static int findRoot() {
        for (int i = 1 ; i <= N ; i++) {
            if (!root[i]) {
                return i;
            }
        }

        return 0;
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = null;
        for (int i = 0 ; i < N-1 ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            root[to] = true;
        }

        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

    static void init() {
        depths = new int[N+1];
        parents = new int[N+1];
        visited = new boolean[N+1];
        root = new boolean[N+1];
        tree = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) tree.add(new ArrayList<>());
    }

}