import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }

        makeDP();

        int K = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            sb.append(dp[y2][x2] - dp[y2][x1-1] - dp[y1-1][x2] + dp[y1-1][x1-1]).append("\n");
        }

        System.out.println(sb);
    }

    static void makeDP() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= M ; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
            }
        }
    }
}