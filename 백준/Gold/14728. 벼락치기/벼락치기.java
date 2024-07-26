import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, T;
    static int[][] dp;
    static Chapter[] chapters;
    static class Chapter {
        int time, score;

        public Chapter(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        chapters = new Chapter[N+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            chapters[i] = new Chapter(time, score);
        }

        dp = new int[N+1][T+1];

        solve();

        System.out.println(dp[N][T]);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= T ; j++) {
                dp[i][j] = dp[i-1][j];

                if (j - chapters[i].time >= 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - chapters[i].time] + chapters[i].score);
            }
        }
    }
}