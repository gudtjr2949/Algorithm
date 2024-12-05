import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = Integer.MAX_VALUE;
    static Node[] app;
    static int[][] dp;
    static class Node {
        int memory, cost;
        public Node(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        app = new Node[N];

        st = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st2.nextToken());
            app[i] = new Node(m, c);
        }

        dp = new int[N][100_001];

        Arrays.sort(app, (o1, o2) -> {
            if (o1.memory == o2.memory) return o1.cost - o2.cost;
            return o1.memory - o2.memory;
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < 100_001 ; j++) {
                if (i == 0) {
                    if (j - app[i].cost >= 0) dp[i][j] = app[i].memory;
                } else {
                    if (j - app[i].cost >= 0) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-app[i].cost] + app[i].memory);
                    else dp[i][j] = dp[i-1][j];
                }

                if (dp[i][j] >= M) answer = Math.min(answer, j);
            }
        }
    }
}