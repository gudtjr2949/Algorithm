import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dp;
    static Node[] nodes;
    static class Node {
        int T, P;
        public Node(int t, int p) {
            T = t;
            P = p;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(dp[0]);
    }

    static void solve() {
        for (int i = N-1 ; i >= 0 ; i--) {
            int T = nodes[i].T;
            int P = nodes[i].P;

            if (i + T <= N) {
                dp[i] = Math.max(dp[i+1], dp[i+T] + P);
            } else {
                dp[i] = dp[i+1];
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(T, P);
        }
    }

    static void init() {
        dp = new int[N+1];
        nodes = new Node[N+1];
    }
}