import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input1(bf);
        makeDP();
        input2(bf);
        System.out.println(sb);
    }

    static void input1(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void input2(BufferedReader bf) throws Exception {
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if (dp[from][to] <= time) sb.append("Enjoy other party").append("\n");
            else sb.append("Stay here").append("\n");
        }
    }

    static void makeDP() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i == j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    static void init() {
        dp = new int[N+1][N+1];
        sb = new StringBuilder();
    }
}