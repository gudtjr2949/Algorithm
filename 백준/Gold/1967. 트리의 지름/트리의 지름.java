import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, answer, tmp;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node {
        int idx, w;

        public Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        boolean[] child = new boolean[N+1];

        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            child[a] = true;
            adj.get(a).add(new Node(b, w));
            adj.get(b).add(new Node(a, w));
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!child[i]) {
                tmp = 0;
                visited = new boolean[N+1];
                visited[i] = true;
                dfs(i, 0);
                answer = Math.max(answer, tmp);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        for (Node next : adj.get(idx)) {
            if (!visited[next.idx]) {
                visited[next.idx] = true;
                dfs(next.idx, sum + next.w);
            }
        }

        tmp = Math.max(tmp, sum);
    }
}