import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int V, end, answer;
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
        V = Integer.parseInt(bf.readLine());

        int[] child = new int[V+1];

        adj = new ArrayList<>();
        for (int i = 0 ; i <= V ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < V ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) break;
                int w = Integer.parseInt(st.nextToken());

                adj.get(start).add(new Node(next, w));
                adj.get(next).add(new Node(start, w));

            }
        }

        answer = Integer.MIN_VALUE;

        visited = new boolean[V+1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[V+1];
        visited[end] = true;
        dfs(end, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        if (answer < sum) {
            end = idx;
            answer = sum;
        }

        for (Node next : adj.get(idx)) {
            if (!visited[next.idx]) {
                visited[next.idx] = true;
                dfs(next.idx, sum + next.w);
            }
        }
    }
}