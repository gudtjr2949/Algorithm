import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<List<Integer>> adj;
    static int[] depth, parents;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(bf.readLine());
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++)
            adj.add(new ArrayList<>());


        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }

        depth = new int[N+1];
        parents = new int[N+1];

        depth[1] = 1;
        parents[1] = 1;

        bfs();

        int M = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            sb.append(lcs(n1, n2)).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        while (!Q.isEmpty()) {
            int parent = Q.poll();

            for (int child : adj.get(parent)) {
                if (!visited[child]) {
                    visited[child] = true;
                    depth[child] = depth[parent]+1;
                    parents[child] = parent;
                    Q.add(child);
                }
            }
        }
    }

    // n2가 무조건 깊은 노드
    static int lcs(int n1, int n2) {
        if (depth[n1] > depth[n2]) {
            int tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        while (depth[n1] != depth[n2]) {
            n2 = parents[n2];
        }

        if (n1 == n2) return n1;

        while (parents[n1] != parents[n2]) {
            n1 = parents[n1];
            n2 = parents[n2];
        }

        return parents[n1];
    }
}