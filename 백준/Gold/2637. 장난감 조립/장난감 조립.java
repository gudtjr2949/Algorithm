import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] use, edge;
    static boolean[] basic;
    static List<List<Node>> adj;
    static class Node {
        int num, cnt;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        use = new int[N+1];
        basic = new boolean[N+1];
        edge = new int[N+1];
        Arrays.fill(basic, true);
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        int M = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            adj.get(X).add(new Node(Y, K));
            basic[X] = false;
            edge[Y]++;
        }

        solve();

        StringBuilder sb = new StringBuilder();

        for (int i = 1 ; i <= N ; i++) {
            if (basic[i]) {
                sb.append(i).append(" ").append(use[i]).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> Q = new LinkedList<>();

        Q.add(new Node(N, 1));
        use[N] = 1;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (Node next : adj.get(now.num)) {
                use[next.num] += now.cnt * next.cnt;
                edge[next.num]--;
                if (edge[next.num] == 0) {
                    Q.add(new Node(next.num, use[next.num]));
                }
            }
        }
    }
}