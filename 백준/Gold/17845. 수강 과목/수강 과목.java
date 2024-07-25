import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Lecture[] lectures;
    static int[][] dp;
    static class Lecture {
        int cost, time;

        public Lecture(int cost, int time) {
            this.cost = cost;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1][N+1];
        lectures = new Lecture[K+1];

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            lectures[i+1] = new Lecture(cost, time);
        }

        solve();

        System.out.println(dp[K][N]);
    }

    private static void solve() {
        for (int i = 1 ; i <= K ; i++) {
            for (int j = 0 ; j <= N ; j++) {
                if (lectures[i].time > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-lectures[i].time] + lectures[i].cost);
                }
            }
        }
    }
}