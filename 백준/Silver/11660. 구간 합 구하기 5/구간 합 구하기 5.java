import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeDP();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int result = dp[y2][x2] - dp[y2][x1-1] - dp[y1-1][x2] + dp[y1-1][x1-1];

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static void makeDP() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }
    }
}