import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents, depths;
    static boolean[] visited;
    static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = null;
        for (int i = 0 ; i < N-1 ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        depths[1] = 1;
        visited[1] = true;
        findDepth(1, 1);
        
        M = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(solve(A, B)).append("\n");
        }

        System.out.println(sb);
    }

    static int solve(int A, int B) {
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
                depths[next] = depth+1;
                parents[next] = idx;
                findDepth(next, depth+1);
            }
        }
    }

    static void init() {
        parents = new int[N+1];
        depths = new int[N+1];
        visited = new boolean[N+1];
        tree = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) tree.add(new ArrayList<>());
    }
}