import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N, T, sum;
    static int[][] dp;
    static Problem[] problems;
    static class Problem {
        int day, money;
        public Problem(int day, int money) {
            this.day = day;
            this.money = money;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        problems = new Problem[N+1];
        dp = new int[T+1][N+1];
        problems[0] = new Problem(0, 0);

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int day = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(day, money);
            sum += money;
        }

        solve();

        System.out.println(sum - dp[T][N]);
    }

    static void solve() {
        Arrays.sort(problems, new Comparator<Problem>() {
            @Override
            public int compare(Problem o1, Problem o2) {
                if (o1.day == o2.day) return o1.money - o2.money;
                else return o1.day - o2.day;
            }
        });


        for (int i = 1 ; i <= T ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                int day = problems[j].day;
                int money = problems[j].money;

                if (i >= day) {
                    dp[i][j] = Math.max(dp[i-day][j-1] + money, Math.max(dp[i-1][j], dp[i][j-1]));
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
    }
}