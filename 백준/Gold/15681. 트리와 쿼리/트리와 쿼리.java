import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] dp;
    static List<List<Integer>> adj;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        dp = new int[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
        visited = new boolean[N+1];

        for (int i = 0 ; i < N-1 ; i++) {
            st = new StringTokenizer(bf.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }

        dp[R] = DFS(R);
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < Q ; i++) {
            sb.append(dp[Integer.parseInt(bf.readLine())]).append("\n");
        }

        System.out.println(sb);
    }

    static int DFS(int R) {
        if (dp[R] != 0) return dp[R];
        visited[R] = true;
        int sum = 1; // 현재까지 연결된 자식 노드 갯수

        for (int child : adj.get(R)) {
            if (!visited[child]) {
                visited[child] = true;
                sum += DFS(child);
            }
        }
        dp[R] = sum;
        return sum;
    }
}