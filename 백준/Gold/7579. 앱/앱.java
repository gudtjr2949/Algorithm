import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, MAX = 100_001, answer;
    static int[][] dp;
    static Node[] apps;
    static class Node {
        int memory, cost;
        public Node(int memory, int cost) {
            this.memory = memory;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        answer = MAX;

        Arrays.sort(apps, (o1, o2) -> {
            if (o1.memory == o2.memory) return o1.cost - o2.cost;
            return o1.memory - o2.memory;
        });

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 0 ; j < MAX ; j++) {
                if (apps[i].cost > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], apps[i].memory + dp[i-1][j-apps[i].cost]);
                }

                if (M <= dp[i][j]) {
                    answer = Math.min(answer, j);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        st = new StringTokenizer(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());

        apps[0] = new Node(0, 0);
        for (int i = 1 ; i <= N ; i++) {
            int memory = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st2.nextToken());
            apps[i] = new Node(memory, cost);
        }
    }

    static void init() {
        dp = new int[N+1][MAX];
        apps = new Node[N+1];
    }
}