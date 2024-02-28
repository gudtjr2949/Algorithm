import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] time, edgeCnt, dp;
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        time = new int[N+1];
        edgeCnt = new int[N+1];
        dp = new int[N+1];
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken());
            while (pre != -1) {
                adj.get(pre).add(i);
                edgeCnt[i]++;
                pre = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        for (int i = 1 ; i <= N ; i++) {
            System.out.println(dp[i]);
        }
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 1 ; i <= N ; i++) {
            dp[i] = time[i];
            if (edgeCnt[i] == 0) {
                Q.add(i);
            }
        }

        while (!Q.isEmpty()) {
            int now = Q.poll();

            for (int next : adj.get(now)) {
                edgeCnt[next]--;
                dp[next] = Math.max(dp[next], dp[now] + time[next]);
                if (edgeCnt[next] == 0) Q.add(next);
            }
        }
    }
}