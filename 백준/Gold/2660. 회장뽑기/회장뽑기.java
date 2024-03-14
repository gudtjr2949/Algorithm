import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, min;
    static int[][] dp;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        dp = new int[N+1][N+1];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (i != j) {
                    dp[i][j] = 100_000;
                }
            }
        }

        while (a != -1 && b != -1) {
            dp[a][b] = 1;
            dp[b][a] = 1;
            st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
        }

        solve();

        Collections.sort(list);

        System.out.println(min + " " + list.size());
        for (int i = 0 ; i < list.size() ; i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    // dp[i] 의 라인에서 최댓값이 가장 작은 i 값 찾기
    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (i != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        min = 100_000;

        for (int i = 1 ; i <= N ; i++) {
            int tmpMax = 0;
            for (int j = 1 ; j <= N ; j++) {
                tmpMax = Math.max(tmpMax, dp[i][j]);
            }

            if (min > tmpMax) {
                min = tmpMax;
                list = new ArrayList<>();
                list.add(i);
            } else if (min == tmpMax) {
                list.add(i);
            }
        }

    }
}