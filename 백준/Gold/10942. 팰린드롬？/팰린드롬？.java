import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init();

        int M = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (S == E) {
                sb.append("1").append("\n");
            } else {
                sb.append(dp[S][E] ? "1" : "0").append("\n");
            }
        }

        System.out.println(sb);
    }

    static void init() {
        for (int i = 0 ; i <= N ; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i <= N - 1; i++) {
            if (arr[i] == arr[i + 1])
                dp[i][i + 1] = true;
        }


        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 2; j <= N ; j++) {
                if (arr[i] == arr[j] && dp[i+1][j-1]) {
                    dp[i][j] = true;
                }
            }
        }
    }
}