import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] dp;
    static Node[] nodes;
    static class Node {
        int weight, cost;
        public Node(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[N][K]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 0 ; j <= K ; j++) {
                if (nodes[i].weight > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], nodes[i].cost + dp[i-1][j-nodes[i].weight]);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(weight, cost);
        }
    }

    static void init() {
        nodes = new Node[N+1];
        dp = new int[N+1][K+1];
    }
}