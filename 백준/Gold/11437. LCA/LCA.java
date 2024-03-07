import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] depth, parents;
    static boolean[] visited;
    static List<List<Integer>> adj;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        depth = new int[N+1];
        parents = new int[N+1];
        visited = new boolean[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }

        BFS();

        int M = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            LCA(node1, node2);
        }

        System.out.println(sb);
    }

    static void BFS() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
        visited[1] = true;

        while (!Q.isEmpty()) {
            int parent = Q.poll();

            for (int child : adj.get(parent)) {
                if (!visited[child]) {
                    visited[child] = true;
                    depth[child] = depth[parent] + 1;
                    parents[child] = parent;
                    Q.add(child);
                }
            }
        }
    }

    static void LCA(int node1, int node2) {

        if (depth[node1] > depth[node2]) {
            int tmp = node1;
            node1 = node2;
            node2 = tmp;
        }

        // node1 과 node2 의 높이를 같게 만들어야 함 -> node2 의 깊이가 더 깊으므로 (= 더 밑에 있으므로) node2 를 계속 부모노드로 갱신함
        while (depth[node1] != depth[node2]) {
            node2 = parents[node2];
        }

        while (node1 != node2) {
            node1 = parents[node1];
            node2 = parents[node2];
        }

        sb.append(node1).append("\n");
    }
}