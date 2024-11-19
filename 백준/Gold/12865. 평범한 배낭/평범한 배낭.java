import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[][] dp;
    static Node[] nodes;
    static class Node {
        int w, v;

        public Node(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1][N+1];

        nodes = new Node[N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.w == o2.w) return o1.v - o2.v;
            return o1.w - o2.w;
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
       for (int i = 1 ; i <= K ; i++) {
           for (int j = 1 ; j <= N ; j++) {
               if (i < nodes[j-1].w) {
                   dp[i][j] = dp[i][j-1];
               } else {
                   dp[i][j] = Math.max(dp[i-nodes[j-1].w][j-1] + nodes[j-1].v, dp[i][j-1]);
               }
           }
       }

       for (int i = 1 ; i <= N ; i++) {
           answer = Math.max(answer, dp[K][i]);
       }
    }
}